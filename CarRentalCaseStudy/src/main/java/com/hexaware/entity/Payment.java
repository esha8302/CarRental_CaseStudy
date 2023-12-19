package com.hexaware.entity;

import java.util.Date;

public class Payment {
	private int paymentID;
	private Lease leaseID;
	private Date paymentDate;
	private double amoun;
	
	public Payment() {}

	public Payment(int paymentID, Lease leaseID, Date paymentDate, double amoun) {
		this.paymentID = paymentID;
		this.leaseID = leaseID;
		this.paymentDate = paymentDate;
		this.amoun = amoun;
	}

	// Getter methods
	public int getPaymentID() {
		return paymentID;
	}

	public Lease getLeaseID() {
		return leaseID;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public double getAmoun() {
		return amoun;
	}

	
	// Setter methods
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public void setLeaseID(Lease leaseID) {
		this.leaseID = leaseID;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public void setAmoun(double amoun) {
		this.amoun = amoun;
	}

	
	@Override
	public String toString() {
		return "Payment [paymentID=" + paymentID + ", leaseID=" + leaseID + ", paymentDate=" + paymentDate + ", amoun="
				+ amoun + "]";
	}
}