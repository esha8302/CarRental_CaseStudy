/**
 * 
 */
package com.hexaware.view;

import java.util.Scanner;

import com.hexaware.controller.CarController;
import com.hexaware.controller.CustomerController;
import com.hexaware.controller.LeaseController;
import com.hexaware.controller.PaymentController;

/**
 * This is the Main module of the Car Rental Application
 */
public class MainModule {

	/**
	 * <p>Presents Car Management Module to the user</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	public static void carView() {
		Scanner scanner = new Scanner(System.in);
		String input = null;

		CarController carController = new CarController();

		do {
			System.out.println("Enter your choice ");
			System.out.println("1. Add Car");
			System.out.println("2. Remove Car");
			System.out.println("3. List Available Cars");
			System.out.println("4. List Rented Cars");
			System.out.println("5. Find car by ID");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1: {
				carController.addCar();
				break;
			}
			case 2: {
				carController.removeCar();
				break;
			}
			case 3: {
				carController.listAvailableCars();
				break;
			}
			case 4: {
				carController.listRentedCars();
				break;
			}
			case 5: {
				carController.findCarById();
				break;
			}
			default: {
				break;
			}
			}
			System.out.println("Do you want to continue with Car Management? ('Y' | 'y')");
			input = scanner.next();

		} while (input.equals("Y") || input.equals("y"));
	}

	/**
	 * <p>Presents Customer Management Module to the user</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	public static void customerView() {
		Scanner scanner = new Scanner(System.in);
		String input = null;

		CustomerController customerController = new CustomerController();

		do {
			System.out.println("Enter your choice ");
			System.out.println("1. Add Customer");
			System.out.println("2. Remove Customer");
			System.out.println("3. List All Customers");
			System.out.println("4. View Customer Details");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1: {
				customerController.addCustomer();
				break;
			}
			case 2: {
				customerController.removeCustomer();
				break;
			}
			case 3: {
				customerController.listCustomers();
				break;
			}
			case 4: {
				customerController.findCustomer();
				break;
			}
			default: {
				break;
			}
			}
			System.out.println("Do you want to continue with Customer Management? ('Y' | 'y')");
			input = scanner.next();

		} while (input.equals("Y") || input.equals("y"));
	}

	/**
	 * <p>Presents Lease Management Module to the user</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	public static void leaseView() {
		Scanner scanner = new Scanner(System.in);
		String input = null;

		LeaseController leaseController = new LeaseController();

		do {
			System.out.println("Enter your choice ");
			System.out.println("1. Create Lease");
			System.out.println("2. View Lease Details");
			System.out.println("3. View all active Leases");
			System.out.println("4. See Lease History");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1: {
				leaseController.createLease();
				break;
			}
			case 2: {
				leaseController.returnLease();
				break;
			}
			case 3: {
				leaseController.listActiveLeases();
				break;
			}
			case 4: {
				leaseController.listLeaseHistory();
				break;
			}
			default: {
				break;
			}
			}
			System.out.println("Do you want to continue with Lease Management? ('Y' | 'y')");
			input = scanner.next();

		} while (input.equals("Y") || input.equals("y"));
	}
	
	/**
	 * <p>Presents Payment Management Module to the user</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	public void paymentView() {
		Scanner scanner = new Scanner(System.in);
		String input = null;
		
		PaymentController paymentController = new PaymentController();
		
		do {
			paymentController.recordPayment();
			System.out.println("Do you want to record another payment? ('Y' | 'y')");
			input = scanner.next();

		}while(input.equals("Y")||input.equals("y"));

	}
	
	/**
	 * <p>Main method</p>
	 * @param String[]
	 * @return void
	 * @since 1.0
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to Car Rental!");
		Scanner scanner = new Scanner(System.in);
		String input = null;
		
		do {
			System.out.println("Enter your choice ");
			System.out.println("1. Car Management");
			System.out.println("2. Customer Management");
			System.out.println("3. Lease Management");
			System.out.println("4. Payment Handling - Record a new Payment");
			int choice = scanner.nextInt();
			
			switch(choice) {
				case 1: {
					carView();
					break;
				}
				case 2: {
					customerView();
					break;
				}
				case 3: {
					leaseView();
					break;
				}
				case 4: {
					PaymentController paymentController = new PaymentController();
					paymentController.recordPayment();
					break;
				}
			}
			System.out.println("Do you want to go back to Main Menu? Y | y");
			input = scanner.next();
			
		} while (input.equals("Y") || input.equals("y"));
		
		System.out.println("Thanks for using Car Rental!");
	}
}
