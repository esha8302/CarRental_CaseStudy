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

import com.hexaware.entity.Lease;
import com.hexaware.exceptions.LeaseNotFoundException;
import com.hexaware.util.DBConnection;
import com.hexaware.util.ResultSetFormatter;

/**
 * Service Layer
 * Implements Database Operations for Lease
 */
public class LeaseDao implements LeaseDaoInterface {
	Connection connection = DBConnection.getConnection();
	PreparedStatement preparedStatement;
	Statement statement;
	ResultSet resultSet;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * <p>Add a new record to Lease Entity</p>
	 * @param int leaseID, int customerID, int carID, LocalDate startDate, LocalDate endDate, String leaseType
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void createLease(int leaseID, int customerID, int carID, LocalDate startDate, LocalDate endDate, String leaseType) {
		try {
//			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO Lease(leaseId, customerId, carId, startDate, endDate, leaseType) VALUES (?, ?, ?, ?, ?, ?) ");
			preparedStatement.setInt(1, leaseID);
			preparedStatement.setInt(2, customerID);
			preparedStatement.setInt(3, carID);
			preparedStatement.setObject(4, startDate);
			preparedStatement.setObject(5, endDate);
			preparedStatement.setString(6, leaseType);
			
			int totalRows = preparedStatement.executeUpdate();
			if (totalRows == 1) {
				System.out.println("1 row added successfully");
				CarDao carDao = new CarDao();
				carDao.updateCarStatus(carID, "NOT AVAILABLE");
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
	}

	/**
	 * <p>Add a new record to Lease Entity</p>
	 * @param int leaseID 
	 * @return Lease
	 * @since 1.0
	 */
	@Override
	public Lease returnLease(int leaseID) throws LeaseNotFoundException {
		try {
//			connection = DBConnection.getConnection();
			preparedStatement= connection.prepareStatement("SELECT leaseId, carId, customerId, startDate, endDate, leaseType FROM Lease WHERE leaseId = ?");
			preparedStatement.setInt(1, leaseID);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Lease lease = ResultSetFormatter.toLease(resultSet);
				return lease;
			}
			else {
				throw new LeaseNotFoundException("Lease does not exists");
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
		return null;
	}

	/**
	 * <p>Add a new record to Lease Entity</p>
	 * @param None
	 * @return List<Lease>
	 * @since 1.0
	 */
	@Override
	public List<Lease> listActiveLeases() {
		LocalDate dateToday = LocalDate.now();
		List<Lease> activeLeaseList = new ArrayList<Lease>();
		try {
//			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement("SELECT leaseId, carId, customerId, startDate, endDate, leaseType FROM Lease WHERE endDate > ?");
			preparedStatement.setObject(1, dateToday);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Lease lease = ResultSetFormatter.toLease(resultSet);
				activeLeaseList.add(lease);
			}
			return activeLeaseList;
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
		return null;
	}

	/**
	 * <p>Add a new record to Lease Entity</p>
	 * @param None
	 * @return List<Lease>
	 * @since 1.0
	 */
	@Override
	public List<Lease> listLeaseHistory() {
		LocalDate dateToday = LocalDate.now();
		List<Lease> activeLeaseList = new ArrayList<Lease>();
		try {
//			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement("SELECT leaseId, carId, customerId, startDate, endDate, leaseType FROM Lease WHERE endDate <= ?");
			preparedStatement.setObject(1, dateToday);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Lease lease = ResultSetFormatter.toLease(resultSet);
				activeLeaseList.add(lease);
			}
			return activeLeaseList;
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
		return null;
	}
}
