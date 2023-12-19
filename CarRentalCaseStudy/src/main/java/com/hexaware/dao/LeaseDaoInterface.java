/**
 * 
 */
package com.hexaware.dao;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.entity.Lease;
import com.hexaware.exceptions.LeaseNotFoundException;

/**
 * 
 */
public interface LeaseDaoInterface {
//	Lease Management
	public void createLease(int leaseId, int customerID, int carID, LocalDate startDate, LocalDate endDate, String leaseType);
	public Lease returnLease(int leaseID) throws LeaseNotFoundException;
	public List<Lease> listActiveLeases();
	public List<Lease> listLeaseHistory();
}
