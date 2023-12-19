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

import com.hexaware.entity.Lease;

/**
 * 
 */
public class PaymentDaoTest {
	static Connection connection;
	PaymentDao paymentDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1");
			createPaymentTable();
		} finally {
			connection.close();
		}
		System.out.println("PaymentDaoTest Setupclass called");
	}

	private static void createPaymentTable() throws SQLException {
//	    Payment Table
		try (PreparedStatement statement = connection.prepareStatement(
				"CREATE TABLE Payment (paymentId INT PRIMARY KEY, leaseId INT, paymentDate DATE, amount DOUBLE)")) {
			statement.executeUpdate();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		connection = DriverManager.getConnection("jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1");

		paymentDao = new PaymentDao();
		paymentDao.setConnection(connection);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		paymentDao = null;
		if (connection != null)
			connection.close();
	}

	/**
	 * Test method for
	 * {@link com.hexaware.dao.PaymentDao#recordPayment(int, com.hexaware.entity.Lease, double)}.
	 */
	@Test
	public void testRecordPayment() {
		// Create test data
		int paymentId = 1;
		Lease lease = new Lease();
		lease.setLeaseID(1);
		double amount = 700.0;

		// Call the recordPayment method with the test data
		paymentDao.recordPayment(paymentId, lease, amount);

		// Verify the result by querying the database
		try (PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM Payment WHERE paymentId = ?")) {
			preparedStatement.setInt(1, paymentId);
			ResultSet resultSet = preparedStatement.executeQuery();

			// Check if the payment is present in the database
			assertTrue(resultSet.next());

			// Verify that the values match the expected values
			assertEquals(paymentId, resultSet.getInt("paymentId"));
			assertEquals(lease.getLeaseID(), resultSet.getInt("leaseId"));
			assertEquals(LocalDate.now(), resultSet.getObject("paymentDate", LocalDate.class));
			assertEquals(amount, resultSet.getDouble("amount"), 0.001); // Delta for double comparison

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
		System.out.println("Closed Connection PeymentDaoTest");
	}

}
