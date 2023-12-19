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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hexaware.entity.Customer;
import com.hexaware.exceptions.CustomerNotFoundException;

/**
 * 
 */
public class CustomerDaoTest {
	static Connection connection;
	CustomerDao customerDao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1");
			createCustomerTable();
		} finally {
			connection.close();
		}
		System.out.println("CustomerDaoTest Setupclass called");
	}
	
	private static void createCustomerTable() throws SQLException {
//	    Customer Table
	    try (PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE Customer (customerId INT PRIMARY KEY, firstName VARCHAR(255), lastName VARCHAR(255), "
                        + "email VARCHAR(255), phone VARCHAR(255))")) {
            statement.executeUpdate();
        }
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		connection = DriverManager.getConnection("jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1");

		customerDao = new CustomerDao();
		customerDao.setConnection(connection);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		customerDao = null;
		if (connection != null) connection.close();
	}

	/**
	 * Test method for {@link com.hexaware.dao.CustomerDao#addCustomer(com.hexaware.entity.Customer)}.
	 */
	@Test
	public void testAddCustomer() {
		Customer testCustomer = new Customer(1, "Amrita", "Rai", "amrita4545@gmail.com", "9898787867");
		customerDao.addCustomer(testCustomer);
		
		try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer WHERE customerId = ?")){
			preparedStatement.setInt(1, testCustomer.getCustomerID());
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the customer is present in the database
            assertTrue(resultSet.next());

            // Verify that the values match the expected values
            assertEquals(testCustomer.getCustomerID(), resultSet.getInt("customerId"));
            assertEquals(testCustomer.getFirstName(), resultSet.getString("firstName"));
            assertEquals(testCustomer.getLastName(), resultSet.getString("lastName"));
            assertEquals(testCustomer.getEmail(), resultSet.getString("email"));
            assertEquals(testCustomer.getPhone(), resultSet.getString("phone"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.hexaware.dao.CustomerDao#removeCustomer(int)}.
	 */
	@Test
	public void testRemoveCustomer() {
		int customerId = 2;
		try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Customer(customerId, firstName, lastName, email, phone) "
                        + "VALUES (?, 'Shubham', 'Sharma', 'shubaham@example.com', '9234567890')")) {
            statement.setInt(1, customerId);
            statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		customerDao.removeCustomer(customerId);

        // Verify that the customer was removed from the database
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer WHERE customerId = ?")) {
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Ensure no rows in the result set, indicating the customer has been removed
            assertFalse(resultSet.next());
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.hexaware.dao.CustomerDao#findCustomerById(int)}.
	 */
	@Test(expected = CustomerNotFoundException.class)
	public void testFindCustomerById() throws CustomerNotFoundException{
		Customer testCustomer1 = new Customer(3, "Aman", "Sen", "amansen2334@gmail.com", "9876567898");
		try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Customer(customerId, firstName, lastName, email, phone) "
                        + "VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, testCustomer1.getCustomerID());
            statement.setString(2, testCustomer1.getFirstName());
            statement.setString(3, testCustomer1.getLastName());
            statement.setString(4, testCustomer1.getEmail());
            statement.setString(5, testCustomer1.getPhone());
            statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Customer customer = customerDao.findCustomerById(testCustomer1.getCustomerID());
            
         // Verify that the values match the expected values
            assertEquals(testCustomer1.getCustomerID(), customer.getCustomerID());
            assertEquals(testCustomer1.getFirstName(), customer.getFirstName());
            assertEquals(testCustomer1.getLastName(), customer.getLastName());
            assertEquals(testCustomer1.getEmail(), customer.getEmail());
            assertEquals(testCustomer1.getPhone(), customer.getPhone());
            
       Customer testCustomer2 = new Customer();
       testCustomer2.setCustomerID(4);
       customerDao.findCustomerById(testCustomer2.getCustomerID());
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		connection.close();
		System.out.println("Closed Connection CustomerDaoTest");	
	}

}
