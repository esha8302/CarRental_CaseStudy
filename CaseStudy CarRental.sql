CREATE DATABASE carRental;
USE carRental;
SHOW TABLES;

CREATE TABLE car(
	carID INT PRIMARY KEY,
    make VARCHAR(100),
    model VARCHAR(100),
    year YEAR,
    dailyrate DECIMAL(5, 2),
    status VARCHAR(50),
    passengerCapacity INT,
    engineCapacity INT,
    CHECK (status IN ("AVAILABLE", "NOT AVAILABLE"))
);

CREATE TABLE Customer(
	customerID INT PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100),
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(10) UNIQUE NOT NULL
);

CREATE TABLE Lease(
	leaseID INT PRIMARY KEY,
    carID INT,
    customerID INT, 
    startDate DATE,
    endDate DATE,
    leaseType VARCHAR(20),
    FOREIGN KEY (carID) REFERENCES Car(carID), 
    FOREIGN KEY (customerID) REFERENCES Customer(customerID), 
    CHECK(leasetype IN ('DAILY', 'MONTHLY'))
    );
    
CREATE TABLE Payment(
	paymentID INT PRIMARY KEY,
    leaseID INT, 
    paymentDate DATE,
    amount DECIMAL(10, 2),
    FOREIGN KEY (leaseID) REFERENCES Lease(leaseID)
);

-- Car table
INSERT INTO Car(carID, make, model, year, dailyRate, status, passengerCapacity, engineCapacity) VALUES 
    (1, 'Toyota', 'Camry', 2022, 50.00, 'AVAILABLE', 4, 1450),
    (2, 'Honda', 'Civic', 2023, 45.00, 'AVAILABLE', 7, 1500),
    (3, 'Ford', 'Focus', 2022, 48.00, 'NOT AVAILABLE', 4, 1400),
    (4, 'Nissan', 'Altima', 2023, 52.00, 'AVAILABLE', 7, 1200),
    (5, 'Chevrolet', 'Malibu', 2022, 47.00, 'AVAILABLE', 4, 1800),
    (6, 'Hyundai', 'Sonata', 2023, 49.00, 'NOT AVAILABLE', 7, 1400),
    (7, 'BMW', '3 Series', 2023, 60.00, 'AVAILABLE', 7, 2499),
    (8, 'Mercedes', 'C-Class', 2022, 58.00, 'AVAILABLE', 8, 2599),
    (9, 'Audi', 'A4', 2022, 55.00, 'NOT AVAILABLE', 4, 2500),
    (10, 'Lexus', 'ES', 2023, 54.00, 'AVAILABLE', 4, 2500);
    
INSERT INTO Customer(customerID, firstname, lastname, email, phone) VALUES
	(1, 'John', 'Doe', 'johndoe@example.com', '5555555555'),
	(2, 'Jane', 'Smith', 'janesmith@example.com', '5551234567'),
	(3, 'Robert', 'Johnson', 'robert@example.com', '5557891234'),
	(4, 'Sarah', 'Brown', 'sarah@example.com', '5554567890'),
	(5, 'David', 'Lee', 'david@example.com', '5559876543'),
	(6, 'Laura', 'Hall', 'laura@example.com', '5552345678'),
	(7, 'Michael', 'Davis', 'michael@example.com', '5558765432'),
	(8, 'Emma', 'Wilson', 'emma@example.com', '5554321098'),
	(9, 'William', 'Taylor', 'william@example.com', '5553216547'),
	(10, 'Olivia', 'Adams', 'olivia@example.com', '5557654321');
    
INSERT INTO Lease(leaseID, carID, customerID, startDate, endDate, leaseType) VALUES
	(1, 1, 1, '2023-01-01', '2023-01-05', 'DAILY'),
	(2, 2, 2, '2023-02-15', '2023-02-28', 'MONTHLY'),
	(3, 3, 3, '2023-03-10', '2023-03-15', 'DAILY'),
	(4, 4, 4, '2023-04-20', '2023-04-30', 'MONTHLY'),
	(5, 5, 5, '2023-05-05', '2023-05-10', 'DAILY'),
	(6, 4, 3, '2023-06-15', '2023-06-30', 'MONTHLY'),
	(7, 7, 7, '2023-07-01', '2023-07-10', 'DAILY'),
	(8, 8, 8, '2023-08-12', '2023-08-15', 'MONTHLY'),
	(9, 3, 3, '2023-09-07', '2023-09-10', 'DAILY'),
	(10, 10, 10, '2023-10-10', '2023-10-31', 'MONTHLY');
    
INSERT INTO Payment(paymentID, leaseID, paymentDate, amount) VALUES
	(1, 1, '2023-01-03', 200.00),
	(2, 2, '2023-02-20', 1000.00),
	(3, 3, '2023-03-12', 75.00),
	(4, 4, '2023-04-25', 900.00),
	(5, 5, '2023-05-07', 60.00),
	(6, 6, '2023-06-18', 1200.00),
	(7, 7, '2023-07-03', 40.00),
	(8, 8, '2023-08-14', 1100.00),
	(9, 9, '2023-09-09', 80.00),
	(10, 10, '2023-10-25', 1500.00);

SELECT * FROM Car;
SELECT * FROM Customer;
SELECT * FROM Lease;
SELECT * FROM Payment;