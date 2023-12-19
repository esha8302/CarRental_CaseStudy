package com.hexaware.entity;

import java.time.Year;

public class Car {
	private int carID;
	private String make;
	private String model;
	private Year year;
	private double dailyRate;
	private String status; // (available, notAvailable)
	private int passengerCapacity;
	private int engineCapacity;
	
	public Car () {}

	public Car (int carID, String make, String model, Year year, double dailyRate,int passengerCapacity,  int engineCapacity) {
		this.carID = carID;
		this.make = make;
		this.model = model;
		this.year = year;
		this.dailyRate = dailyRate;
		this.status = "AVAILABLE"; // default
		this.passengerCapacity = passengerCapacity;
		this.engineCapacity = engineCapacity;
	}
	
//	Getters
	public int getCarID() {
		return carID;
	}

	public String getMake() {
		return make;
	}
	
	public String getModel() {
		return model;
	}
	
	public Year getYear() {
		return year;
	}
	
	public double getDailyRate() {
		return dailyRate;
	}
	
	public String getStatus() {
		return status;
	}
	
	public int getPassengerCapacity() {
		return passengerCapacity;
	}
	
	public int getEngineCapacity() {
		return engineCapacity;
	}
	
	 // Setters 
	public void setCarID(int carID) {
		this.carID = carID;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public void setYear(Year year) {
		this.year = year;
	}
	
	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	public void setEngineCapacity(int engineCapacity) {
		this.engineCapacity = engineCapacity;
	}


	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}

	@Override
	public String toString() {
		return "Car [carID=" + carID + ", make=" + make + ", model=" + model + ", year=" + year + ", dailyRate="
				+ dailyRate + ", status=" + status + ", passengerCapacity=" + passengerCapacity + ", engineCapacity="
				+ engineCapacity + "]";
	}

	
}