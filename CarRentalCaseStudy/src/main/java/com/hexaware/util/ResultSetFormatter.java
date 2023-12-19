package com.hexaware.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;

import com.hexaware.dao.CarDao;
import com.hexaware.dao.CustomerDao;
import com.hexaware.entity.Car;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Lease;
import com.hexaware.exceptions.CarNotFoundException;
import com.hexaware.exceptions.CustomerNotFoundException;

public class ResultSetFormatter {
	static CustomerDao customerDao = new CustomerDao();
	static CarDao carDao = new CarDao();
	
	public static Lease toLease(ResultSet resultSet) throws SQLException{
			Lease lease = new Lease();
			
			lease.setLeaseID(resultSet.getInt("leaseId"));
			
			int carId = resultSet.getInt("carId");
			Car car;
			try {
				car = carDao.findCarById(carId);
			} catch (CarNotFoundException e) {
				System.out.println("Car info could not be found for the car associated with this Lease.");
				car = new Car();
				car.setCarID(carId);
			}
			lease.setCar(car);
			
			int customerId = resultSet.getInt("customerId");
			Customer customer;
			try {
				customer = customerDao.findCustomerById(customerId);
			} catch (CustomerNotFoundException e) {
				System.out.println("Customer Details could not be found for the customer associated with this Lease");
				customer = new Customer();
				customer.setCustomerID(customerId);
			}
			lease.setCustomer(customer);
			
			lease.setStartDate(resultSet.getObject("startDate", LocalDate.class));
			lease.setEndDate(resultSet.getObject("endDate", LocalDate.class));
			lease.setType(resultSet.getString("leaseType"));
			
			return lease;
	}
	
	public static Car toCar(ResultSet resultSet) throws SQLException{
		Car car = new Car();
		car.setCarID(resultSet.getInt("carId"));
		car.setMake(resultSet.getString("make"));
		car.setModel(resultSet.getString("model"));
		
		Year year = Year.of(resultSet.getInt("year"));
		car.setYear(year);
		
		car.setDailyRate(resultSet.getDouble("dailyRate"));
		car.setStatus(resultSet.getString("status"));
		car.setPassengerCapacity(resultSet.getInt("passengerCapacity"));
		car.setEngineCapacity(resultSet.getInt("engineCapacity"));
		
		return car;
	}
}
