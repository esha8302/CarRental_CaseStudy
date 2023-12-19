/**
 * 
 */
package com.hexaware.controller;

import java.time.Year;
import java.util.List;
import java.util.Scanner;

import com.hexaware.dao.CarDao;
import com.hexaware.entity.Car;
import com.hexaware.exceptions.CarNotFoundException;

/**
 * Controls Car Management
 * Takes input from user
 * Calls Service Layer(Dao)
 */
public class CarController implements CarInterface {
	Scanner scanner = new Scanner(System.in);
	CarDao carDao = new CarDao();

	/**
	 * <p>Adds a new Car Object</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void addCar() {
		System.out.println("Enter Car ID");
		int carId = scanner.nextInt();
		
		System.out.println("Enter Company/Brand of car");
		String make = scanner.next();
		
		System.out.println("Enter car Model");
		String model = scanner.next();
		
		System.out.println("Enter the manufacturing year of car");
		String yearInput = scanner.next();
		Year year = Year.parse(yearInput);
		
		System.out.println("Enter Daily Rate for the Car");
		double dailyRate = scanner.nextDouble();
		
		System.out.println("Enter Passenger Capacity of car");
		int passengerCapacity = scanner.nextInt();
		
		System.out.println("Enter Engine capacity");
		int engineCapacity = scanner.nextInt();
		
		Car car = new Car(carId, make, model, year, dailyRate, passengerCapacity, engineCapacity);
		
		carDao.addCar(car);
	}

	/**
	 * <p>Removes a Car Object</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void removeCar() {
		System.out.println("Enter Car ID");
		int carId = scanner.nextInt();
		
		carDao.removeCar(carId);
	}

	/**
	 * <p>Prints all Available Cars</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void listAvailableCars() {
		List<Car> carList = carDao.listAvailableCars();
		for ( Car car: carList){
			System.out.println(car.toString());
		}
	}

	/**
	 * <p>Prints cars that are Not Available</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void listRentedCars() {
		List<Car> rentedCarList = carDao.listRentedCars();
		for ( Car car: rentedCarList){
			System.out.println(car.toString());
		}
	}

	/**
	 * <p>Prints a Car Object</p>
	 * @param None
	 * @return void
	 * @since 1.0
	 */
	@Override
	public void findCarById() {
		System.out.println("Enter Car ID");
		int carId = scanner.nextInt();
		
		try {
			Car car = carDao.findCarById(carId);
			System.out.println(car.toString());
		} catch (CarNotFoundException e) {
			System.out.println(e.toString());
		}
	}
	
}
