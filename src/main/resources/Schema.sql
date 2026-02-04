-- Create Database
CREATE DATABASE IF NOT EXISTS parkinglotDB;
USE parkinglotDB;

-- Drop tables if they exist
DROP TABLE IF EXISTS ParkingEntry;
DROP TABLE IF EXISTS vehicle;
DROP TABLE IF EXISTS Slot;
DROP TABLE IF EXISTS parking_lot;

-- ===========================
-- Table: Parking Lot
-- ===========================
CREATE TABLE parking_lot (
    id INT AUTO_INCREMENT PRIMARY KEY,
    parking_name VARCHAR(50) NOT NULL,
    total_car_slot INT NOT NULL,
    total_bike_slot INT NOT NULL
);

-- ===========================
-- Table: Slot
-- ===========================
CREATE TABLE Slot (
    id INT AUTO_INCREMENT PRIMARY KEY,
    parking_id INT NOT NULL,
    slot_no INT NOT NULL,
    vehicle_type ENUM('Car', 'Bike') NOT NULL,
    status ENUM('EMPTY', 'Filled') DEFAULT 'EMPTY',
    UNIQUE(parking_id, slot_no),
    CONSTRAINT fk_parking FOREIGN KEY (parking_id) REFERENCES parking_lot(id)
);

-- ===========================
-- Table: Vehicle
-- ===========================
CREATE TABLE vehicle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_type ENUM('Car', 'Bike') NOT NULL,
    License_Plate VARCHAR(15) UNIQUE NOT NULL,
    vehicle_owner_name VARCHAR(50) NOT NULL,
    vehicle_registration_id VARCHAR(20) NOT NULL,
    driver_name VARCHAR(50) NOT NULL,
    driver_license_id VARCHAR(20) NOT NULL,
    vehicle_brand VARCHAR(50)
);

-- ===========================
-- Table: Parking Entry
-- ===========================
CREATE TABLE ParkingEntry (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT NOT NULL,
    slot_id INT NOT NULL,
    Entry_Time DATETIME NOT NULL,
    Exit_Time DATETIME,
    Total_amount DECIMAL(10, 2),
    CONSTRAINT fk_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle(id),
    CONSTRAINT fk_slot FOREIGN KEY (slot_id) REFERENCES Slot(id),
    CONSTRAINT chk_time CHECK (
        Exit_Time IS NULL
        OR Exit_Time >= Entry_Time
    )
);

-- Optional initial data for slots
/*INSERT INTO Slot(parking_id, slot_no, vehicle_type, status)
VALUES (1, 1, 'Car', 'EMPTY'),
       (1, 2, 'Car', 'EMPTY'),
       (1, 3, 'Car', 'EMPTY'),
       (1, 4, 'Car', 'EMPTY'),
       (1, 5, 'Car', 'EMPTY'),
       (1, 6, 'Bike', 'EMPTY'),
       (1, 7, 'Bike', 'EMPTY'),
       (1, 8, 'Bike', 'EMPTY'),
       (1, 9, 'Bike', 'EMPTY'),
       (1, 10, 'Bike', 'EMPTY');*/



SELECT * FROM vehicle;