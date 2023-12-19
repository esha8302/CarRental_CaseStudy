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

import com.hexaware.entity.Lease;
import com.hexaware.util.DBConnection;

/**
 * Service Layer
 * Implements Database Operations for Payment
 */
public class PaymentDao implements PaymentDaoInterface {
	Connection connection = DBConnection.getConnection();
	PreparedStatement preparedStatement;
	Statement statement;
	ResultSet resultSet;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * <p>Add a new record to Payment Entity</p>
	 * @param int paymentId, Lease lease, double amount
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void recordPayment(int paymentId, Lease lease, double amount) {
		LocalDate dateToday = LocalDate.now();
		try {
//			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO Payment(paymentId, leaseId, paymentDate, amount) VALUES (?, ?, ?, ?)");
			preparedStatement.setInt(1, paymentId);
			preparedStatement.setInt(2, lease.getLeaseID());
			preparedStatement.setObject(3, dateToday);
			preparedStatement.setDouble(4, amount);
			
			int totalRows = preparedStatement.executeUpdate();
			if (totalRows == 1) {
				System.out.println("1 row added successfully");
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

}
