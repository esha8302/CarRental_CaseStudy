/**
 * 
 */
package com.hexaware.controller;

import java.util.List;
import java.util.Scanner;

import com.hexaware.dao.CustomerDao;
import com.hexaware.entity.Customer;
import com.hexaware.exceptions.CustomerNotFoundException;

/**
 * Controls Customer Management
 * Takes input from user
 * Calls Service Layer(Dao)
 */
public class CustomerController implements CustomerInterface {
	Scanner scanner = new Scanner(System.in);
	CustomerDao customerDao = new CustomerDao();
	
	/**
	 * <p>Adds a new Customer Object</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void addCustomer() {
		System.out.println("Enter Customer ID");
		int customerId = scanner.nextInt();
		
		System.out.print("Enter First Name ");
		String firstName = scanner.next();
		
		System.out.print("Enter Last Name ");
		String lastName = scanner.next();
		
		System.out.print("Enter Email Address ");
		String email = scanner.next();
		
		System.out.print("Enter Phone Number");
		String phone = scanner.next();
		
		scanner.nextLine(); // To avoid breaking input due to /n character
		
		Customer customer = new Customer(customerId, firstName, lastName, email, phone);
		
		customerDao.addCustomer(customer);
	}

	/**
	 * <p>Removes a Customer Object</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void removeCustomer() {
		System.out.println("Enter Customer ID");
		int customerId = scanner.nextInt();
		
		customerDao.removeCustomer(customerId);
	}

	/**
	 * <p>Prints all Customer Objects</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void listCustomers() {
		List<Customer> customerList = customerDao.listCustomers();
		for (Customer customer: customerList) {
			System.out.println(customer.toString());
		}
	}

	/**
	 * <p>Prints a single Customer Object</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void findCustomer() {
		System.out.println("Enter Customer ID");
		int customerId = scanner.nextInt();
		
		try {
			Customer customer = customerDao.findCustomerById(customerId);
		} catch (CustomerNotFoundException e) {
			System.out.println(e.toString());
		}
	}

}
