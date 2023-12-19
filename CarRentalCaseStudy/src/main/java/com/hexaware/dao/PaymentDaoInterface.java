/**
 * 
 */
package com.hexaware.dao;

import com.hexaware.entity.Lease;

/**
 * 
 */
public interface PaymentDaoInterface {
//	Payment Handling
	public void recordPayment(int paymentId, Lease lease, double amount);
}
