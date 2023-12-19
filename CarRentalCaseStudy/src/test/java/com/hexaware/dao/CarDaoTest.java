/**
 * 
 */
package com.hexaware.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hexaware.entity.Car;
import com.hexaware.exceptions.CarNotFoundException;

/**
 * 
 */
public class CarDaoTest {
	static Connection connection;
	CarDao carDao;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1");
			createCarTable();
		} finally {
			connection.close();
		}
		System.out.println("CarDaoTest Setupclass called");
	}

	private static void createCarTable() throws SQLException {
//	    Car Table
		try (Statement statement = connection.createStatement()) {
			statement.execute("CREATE TABLE Car (" + "carId INT PRIMARY KEY, " + "make VARCHAR(255), "
					+ "model VARCHAR(255), " + "year YEAR, " + "dailyRate DOUBLE, " + "status VARCHAR(255), "
					+ "passengerCapacity INT, " + "engineCapacity INT)");
		}
	}

	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		connection = DriverManager.getConnection("jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1");

		carDao = new CarDao();
		carDao.setConnection(connection);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		carDao = null;
		if (connection != null) connection.close();
	}

	/**
	 * Test method for
	 * {@link com.hexaware.dao.CarDao#addCar(com.hexaware.entity.Car)}.
	 */
	@Test
	public void testAddCar() {
		Year year = Year.parse("2021");
		Car testCar = new Car(1, "Honda", "City", year, 140.00, 4, 1450);
		carDao.addCar(testCar);
		
		try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Car WHERE carId = ?")) {
			preparedStatement.setInt(1, testCar.getCarID());
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the car is present in the database
            assertTrue(resultSet.next());

            // Verify that the values match the expected values
            assertEquals(testCar.getCarID(), resultSet.getInt("carId"));
            assertEquals(testCar.getMake(), resultSet.getString("make"));
            assertEquals(testCar.getModel(), resultSet.getString("model"));
            assertEquals(testCar.getYear().getValue(), resultSet.getInt("year"));
            assertEquals(testCar.getDailyRate(), resultSet.getDouble("dailyRate"), 0.001); // Delta for double comparison
            assertEquals(testCar.getStatus(), resultSet.getString("status"));
            assertEquals(testCar.getPassengerCapacity(), resultSet.getInt("passengerCapacity"));
            assertEquals(testCar.getEngineCapacity(), resultSet.getInt("engineCapacity"));

            // Ensure no more rows in the result set
            assertFalse(resultSet.next());
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.hexaware.dao.CarDao#removeCar(int)}.
	 */
	@Test
	public void testRemoveCar(){
		// Insert a Test car 
				int carId = 2;
		        try (PreparedStatement statement = connection.prepareStatement(
		                "INSERT INTO Car(carId, make, model, year, dailyRate, status, passengerCapacity, engineCapacity) "
		                        + "VALUES (?, 'Test Make', 'Test Model', 2022, 100.0, 'Available', 5, 200)")) {
		            statement.setInt(1, carId);
		            statement.executeUpdate();
		        } catch(SQLException e) {
		        	e.printStackTrace();
		        }

		        carDao.removeCar(carId);

		        // Verify that the car was removed
		        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Car WHERE carId = ?")) {
		            preparedStatement.setInt(1, carId);
		            ResultSet resultSet = preparedStatement.executeQuery();

		            // Check that no rows in the result set, indicating the car has been removed
		            assertFalse(resultSet.next());
		        } catch(SQLException e) {
		        	e.printStackTrace();
		        }
	}

	/**
	 * Test method for {@link com.hexaware.dao.CarDao#findCarById(int)}.
	 */
	@Test(expected = CarNotFoundException.class)
	public void testFindCarById() throws CarNotFoundException{
		Year year = Year.parse("2022");
		Car testCar1 = new Car(3, "Tata", "Nexus", year, 210.00, 4, 1200);
		Car testCar2 = new Car();
		testCar2.setCarID(4);
		
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Car(carId, make, model, year, dailyRate, status, passengerCapacity, engineCapacity) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
        	preparedStatement.setInt(1, testCar1.getCarID());
        	preparedStatement.setString(2, testCar1.getMake());
        	preparedStatement.setString(3, testCar1.getModel());
        	preparedStatement.setObject(4, testCar1.getYear().getValue());
        	preparedStatement.setDouble(5, testCar1.getDailyRate());
        	preparedStatement.setString(6, testCar1.getStatus());
        	preparedStatement.setInt(7, testCar1.getPassengerCapacity());
        	preparedStatement.setInt(8, testCar1.getEngineCapacity());
        	preparedStatement.executeUpdate();
        } catch(SQLException e) {
        	e.printStackTrace();
        }
        
        Car car = carDao.findCarById(testCar1.getCarID());
        
        assertTrue(car != null);
        
        assertEquals(testCar1.getCarID(), car.getCarID());
        assertEquals(testCar1.getMake(), car.getMake());
        assertEquals(testCar1.getModel(), car.getModel());
        assertEquals(testCar1.getYear().getValue(), car.getYear().getValue());
        assertEquals(testCar1.getDailyRate(), car.getDailyRate(), 0.001);
        assertEquals(testCar1.getStatus(), car.getStatus());
        assertEquals(testCar1.getPassengerCapacity(), car.getPassengerCapacity());
        assertEquals(testCar1.getEngineCapacity(), car.getEngineCapacity());
        
        carDao.findCarById(testCar2.getCarID());
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		connection.close();
		System.out.println("Closed Connection CarDaoTest");	
	}


}
