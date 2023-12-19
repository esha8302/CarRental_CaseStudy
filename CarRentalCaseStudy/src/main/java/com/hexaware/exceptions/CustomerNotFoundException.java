package com.hexaware.exceptions;

public class CustomerNotFoundException extends Exception {
	String message;
	public CustomerNotFoundException(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "CustomerNotFoundException [message=" + message + "]";
	}
}
