/**
 * 
 */
package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.entity.Car;
import com.hexaware.exceptions.CarNotFoundException;
import com.hexaware.util.DBConnection;
import com.hexaware.util.ResultSetFormatter;

/**
 * Service Layer
 * Implements Database Operations for Car
 */
public class CarDao implements CarDaoInterface {
	Connection connection = DBConnection.getConnection();
	PreparedStatement preparedStatement;
	Statement statement;
	ResultSet resultSet;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void updateCarStatus(int carId, String status) {
		try {
			preparedStatement = connection.prepareStatement("UPDATE Car SET status = ? WHERE carId = ?");
			preparedStatement.setInt(1, carId);
			preparedStatement.setString(2, status);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>Add a new record to Car Entity</p>
	 * @param Car car
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void addCar(Car car) {
		try {
//			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(
					"INSERT INTO Car(carId, make, model, year, dailyRate, status, passengerCapacity, engineCapacity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, car.getCarID());
			preparedStatement.setString(2, car.getMake());
			preparedStatement.setString(3, car.getModel());
			;
			preparedStatement.setInt(4, car.getYear().getValue());
			preparedStatement.setDouble(5, car.getDailyRate());
			preparedStatement.setString(6, car.getStatus());
			preparedStatement.setInt(7, car.getPassengerCapacity());
			preparedStatement.setInt(8, car.getEngineCapacity());

			int totalRows = preparedStatement.executeUpdate();
			if (totalRows == 1)
				System.out.println("1 row added successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}


	}

	/**
	 * <p>Deleted a record from Car Entity</p>
	 * @param int carId
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void removeCar(int carId) {
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM Car WHERE carID = ?");
			preparedStatement.setInt(1, carId);
			int totalRows = preparedStatement.executeUpdate();
			if (totalRows == 1)
				System.out.println("1 row deleted");

		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}

	/**
	 * <p>Retrieves all available cars from Car Entity</p>
	 * @param None
	 * @return List<Car>
	 * @since 1.0
	 */
	@Override
	public List<Car> listAvailableCars() {
		List<Car> carList = new ArrayList<Car>();

		try {
//			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT carId, make, model, year, dailyRate, status, passengerCapacity, engineCapacity FROM Car WHERE carId NOT IN (SELECT carId FROM Lease WHERE startDate<=? AND endDate>?)");
			LocalDate dateToday = LocalDate.now();
			preparedStatement.setObject(1, dateToday);
			preparedStatement.setObject(2, dateToday);
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Car car = ResultSetFormatter.toCar(resultSet);
				carList.add(car);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		return carList;
	}

	/**
	 * <p>Retrieves all Not Available cars from Car Entity</p>
	 * @param None
	 * @return List<Car>
	 * @since 1.0
	 */
	@Override
	public List<Car> listRentedCars() {
		List<Car> rentedCarList = new ArrayList<Car>();

		try {
//			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement("SELECT carId, make, model, year, dailyRate, status, passengerCapacity, engineCapacity FROM Car WHERE carId IN (SELECT carId FROM Lease WHERE startDate<=? AND endDate>?) ");
			LocalDate dateToday = LocalDate.now();
			preparedStatement.setObject(1, dateToday);
			preparedStatement.setObject(2, dateToday);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Car car = ResultSetFormatter.toCar(resultSet);
				rentedCarList.add(car);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		return rentedCarList;
	}

	/**
	 * <p>Retrieves a car from Car Entity</p>
	 * @param int carId
	 * @return Car
	 * @since 1.0
	 */
	@Override
	public Car findCarById(int carId) throws CarNotFoundException {
		Car car = new Car();
		try {
//			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT carId, make, model, year, dailyRate, status, passengerCapacity, engineCapacity FROM Car WHERE carId = ? ");

			preparedStatement.setInt(1, carId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				car = ResultSetFormatter.toCar(resultSet);
			} else {
				throw new CarNotFoundException("Car with carId "+carId+" does not exist.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		return car;
	}
}
