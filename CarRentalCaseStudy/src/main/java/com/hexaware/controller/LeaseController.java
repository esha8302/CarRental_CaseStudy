/**
 * 
 */
package com.hexaware.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.hexaware.dao.LeaseDao;
import com.hexaware.entity.Lease;
import com.hexaware.exceptions.LeaseNotFoundException;

/**
 * Controls Lease Management
 * Takes input from user
 * Calls Service Layer(Dao)
 */

public class LeaseController implements LeaseInterface {
	Scanner scanner = new Scanner(System.in);
	LeaseDao leaseDao = new LeaseDao();

	/**
	 * <p>Adds a new Lease Object</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void createLease() {
		System.out.println("Enter Lease ID ");
		int leaseId = scanner.nextInt();
		
		System.out.println("Enter car ID");
		int carId = scanner.nextInt();
		
		System.out.println("Enter Customer ID");
		int customerId = scanner.nextInt();
		
		System.out.println("Enter startDate");
		String startDateString = scanner.next();
		LocalDate startDate = LocalDate.parse(startDateString);
		
		System.out.println("Enter endDate");
		String endDateString = scanner.next();
		LocalDate endDate = LocalDate.parse(endDateString);
		
		System.out.println("Enter Lease Type (DAILY, MONTHLY)");
		String type = scanner.next();
		
		leaseDao.createLease(leaseId, customerId, carId, startDate, endDate, type);
	}

	/**
	 * <p>Retrieve a Lease Object</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void returnLease() {
		System.out.println("Enter Lease ID ");
		int leaseId = scanner.nextInt();
		try {
			Lease lease = leaseDao.returnLease(leaseId);
			System.out.println(lease.toString());
		} catch (LeaseNotFoundException e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * <p>Prints all active leases</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void listActiveLeases() {
		List<Lease> activeLeaseList = leaseDao.listActiveLeases();
		if (activeLeaseList.size() == 0) {
			System.out.println("No active lease found!");
		}
		for (Lease lease : activeLeaseList) {
			System.out.println(lease.toString());
		}
	}
	
	/**
	 * <p>Prints the lease history</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void listLeaseHistory() {
		List<Lease> leaseHistory = leaseDao.listLeaseHistory();
		if (leaseHistory.size() == 0) {
			System.out.println("Lease History is empty!");
		}
		for (Lease lease : leaseHistory) {
			System.out.println(lease.toString());
		}

	}

}
