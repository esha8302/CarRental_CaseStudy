/**
 * 
 */
package com.hexaware.dao;

import java.util.List;

import com.hexaware.entity.Car;
import com.hexaware.exceptions.CarNotFoundException;

/**
 * 
 */
public interface CarDaoInterface {
//	Car Management
	public void addCar(Car car);
	public void removeCar(int carId);
	public List<Car> listAvailableCars();
	public List<Car> listRentedCars();
	public Car findCarById(int carId) throws CarNotFoundException;

}
