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
import java.time.LocalDate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */
public class LeaseDaoTest {
	static Connection connection;
	LeaseDao leaseDao;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1");
			createLeaseTable();
		} finally {
			connection.close();
		}
		System.out.println("LeaseDaoTest Setupclass called");
	}

	private static void createLeaseTable() throws SQLException {
//		Lease Table
		try (PreparedStatement statement = connection
				.prepareStatement("CREATE TABLE Lease (leaseId INT PRIMARY KEY, customerId INT, carId INT, "
						+ "startDate DATE, endDate DATE, leaseType VARCHAR(255))")) {
			statement.executeUpdate();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		connection = DriverManager.getConnection("jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1");

		leaseDao = new LeaseDao();
		leaseDao.setConnection(connection);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		leaseDao = null;
		if (connection != null) connection.close();
	}

	/**
	 * Test method for
	 * {@link com.hexaware.dao.LeaseDao#createLease(int, int, int, java.time.LocalDate, java.time.LocalDate, java.lang.String)}.
	 */
	@Test
	public void testCreateLease() {
		int leaseID = 1;
        int customerID = 1;
        int carID = 1;
		LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(7);
        String leaseType = "DAILY";

        // Call the createLease method with the test data
        leaseDao.createLease(leaseID, customerID, carID, startDate, endDate, leaseType);

        // Verify the result by querying the database
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Lease WHERE leaseId = ?")) {
            preparedStatement.setInt(1, leaseID);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the lease is present in the database
            assertTrue(resultSet.next());

            // Verify that the values match the expected values
            assertEquals(leaseID, resultSet.getInt("leaseId"));
            assertEquals(customerID, resultSet.getInt("customerId"));
            assertEquals(carID, resultSet.getInt("carId"));
            assertEquals(startDate, resultSet.getObject("startDate", LocalDate.class));
            assertEquals(endDate, resultSet.getObject("endDate", LocalDate.class));
            assertEquals(leaseType, resultSet.getString("leaseType"));

            // Ensure no more rows in the result set
            assertFalse(resultSet.next());
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		connection.close();
		System.out.println("Closed Connection LeaseDaoTest");	
	}

}
