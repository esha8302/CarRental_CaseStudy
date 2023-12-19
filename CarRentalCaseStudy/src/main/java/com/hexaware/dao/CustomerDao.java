/**
 * 
 */
package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.entity.Customer;
import com.hexaware.exceptions.CustomerNotFoundException;
import com.hexaware.util.DBConnection;

/**
 * Service Layer
 * Implements Database Operations for Customer
 */
public class CustomerDao implements CustomerDaoInterface {
	Connection connection = DBConnection.getConnection();
	PreparedStatement preparedStatement;
	Statement statement;
	ResultSet resultSet;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * <p>Add a new record to Customer Entity</p>
	 * @param Customer customer
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void addCustomer(Customer customer) {
		try {
//			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO Customer(customerId, firstName, lastName, email, phone) VALUES (?, ?, ?, ?, ?) ");
			preparedStatement.setInt(1, customer.getCustomerID());
			preparedStatement.setString(2, customer.getFirstName());
			preparedStatement.setString(3, customer.getLastName());
			preparedStatement.setString(4, customer.getEmail());
			preparedStatement.setString(5, customer.getPhone());
			
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

	/**
	 * <p>Remove a record from Customer Entity</p>
	 * @param int customerId
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void removeCustomer(int customerID) {
		try {
//			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM Customer WHERE customerId = ?");
			preparedStatement.setInt(1, customerID);
			int totalRows = preparedStatement.executeUpdate();
			if (totalRows == 1) {
				System.out.println("1 row deleted");
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
	 * <p>Retrieves all records from Customer Entity</p>
	 * @param Customer customer
	 * @return void
	 * @since 1.0
	 */
	@Override
	public List<Customer> listCustomers() {
		List<Customer> customerList = new ArrayList<Customer>();
		
		try {
//			connection = DBConnection.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT customerId, firstName, lastName, email, phone FROM Customer");
			
			while(resultSet.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(resultSet.getInt("customerId"));
				customer.setFirstName(resultSet.getString("firstName"));
				customer.setLastName(resultSet.getString("lastName"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPhone(resultSet.getString("phone"));
				
				customerList.add(customer);
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
		return customerList;
	}

	/**
	 * <p>Retrieve a record from the Customer Entity</p>
	 * @param int customerId
	 * @return Customer customer
	 * @since 1.0
	 */
	@Override
	public Customer findCustomerById(int customerID) throws CustomerNotFoundException {
		try {
//			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement("SELECT customerId, firstName, lastName, email, phone FROM Customer WHERE customerID = ?");
			preparedStatement.setInt(1, customerID);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(resultSet.getInt("customerId"));
				customer.setFirstName(resultSet.getString("firstName"));
				customer.setLastName(resultSet.getString("lastName"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPhone(resultSet.getString("phone"));
				return customer;
			}else {
				throw new CustomerNotFoundException("Customer does not exist");
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
}
