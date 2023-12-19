package com.hexaware.entity;

import java.time.LocalDate;

public class Lease {
	private int leaseID;
	private Car car; 
	private Customer customer;
	private LocalDate startDate;
	private LocalDate endDate;
	private String type; // (DailyLease, MonthlyLease)
	
	public Lease() {}
	
	public Lease(int leaseID, Car car, Customer customer, LocalDate startDate, LocalDate endDate, String type) {
		this.leaseID = leaseID;
		this.car = car;
		this.customer = customer;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
	}


	// Getter methods
	public int getLeaseID() {
		return leaseID;
	}

	public Car getCar() {
		return car;
	}

	public Customer getCustomer() {
		return customer;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public String getType() {
		return type;
	}
	
	
	// Setters 
	public void setLeaseID(int leaseID) {
		this.leaseID = leaseID;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void setCustomer(Customer customerID) {
		this.customer = customerID;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Lease [leaseID=" + leaseID + ", car=" + car.toString() + ", customerID=" + customer.toString() + ", startDate="
				+ startDate + ", endDate=" + endDate + ", type=" + type + "]";
	}
}