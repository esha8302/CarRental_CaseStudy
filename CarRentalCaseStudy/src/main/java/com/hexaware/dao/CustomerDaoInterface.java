package com.hexaware.dao;

import java.util.List;

import com.hexaware.entity.Customer;
import com.hexaware.exceptions.CustomerNotFoundException;

public interface CustomerDaoInterface {
//	Customer Management
	public void addCustomer(Customer customer);
	public void removeCustomer(int customerID);
	public List<Customer> listCustomers();
	public Customer findCustomerById(int customerID) throws CustomerNotFoundException;
}
