package com.hexaware.exceptions;

public class CarNotFoundException extends Exception {
	String message; 
	public CarNotFoundException(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "CarNotFoundException [message=" + message + "]";
	}
	
}
