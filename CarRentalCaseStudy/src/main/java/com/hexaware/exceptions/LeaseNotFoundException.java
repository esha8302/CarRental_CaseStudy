package com.hexaware.exceptions;

public class LeaseNotFoundException extends Exception {
	String message;
	public LeaseNotFoundException(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "LeaseNotFoundException [message=" + message + "]";
	}
}
