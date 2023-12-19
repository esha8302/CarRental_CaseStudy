/**
 * 
 */
package com.hexaware.controller;

import java.util.Scanner;

import com.hexaware.dao.PaymentDao;
import com.hexaware.entity.Lease;

/**
 * Controls Payment Management
 * Takes input from user
 * Calls Service Layer(Dao)
 */
public class PaymentController implements PaymentInterface {
	Scanner scanner = new Scanner(System.in);
	PaymentDao paymentDao = new PaymentDao();
	
	/**
	 * <p>Adds a new Payment Object</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void recordPayment() {
		System.out.println("Enter Payment Id");
		int paymentId = scanner.nextInt();
		
		System.out.println("Enter lease Id");
		int leaseId = scanner.nextInt();
		Lease lease = new Lease();
		lease.setLeaseID(leaseId);
		
		System.out.println("Enter payment amount ");
		double amount = scanner.nextDouble();
		
		paymentDao.recordPayment(paymentId, lease, amount);
	}

}
