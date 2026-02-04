# Parking Lot Management System

![Java](https://img.shields.io/badge/Java-17+-blue)
![Maven](https://img.shields.io/badge/Maven-Build-orange)
![Database](https://img.shields.io/badge/Database-MySQL-blue)
![Status](https://img.shields.io/badge/Status-Completed-success)
![Tests](https://img.shields.io/badge/Tests-Present-lightgrey)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

---

A Java-based Parking Lot Management System with **MySQL integration**, billing logic, and core parking operations.  
This project demonstrates modular design, database connectivity using JDBC, and unit testing using JUnit.

---

## 🔍 Overview

This application simulates key parking lot functionalities such as:

- Vehicle entry and exit
- Parking slot allocation
- Parking fee calculation
- Data persistence using a database
- Clean separation between model, service, and database layers

The project is console-based and focuses on backend fundamentals.

---

## 📁 Project Structure

```text
parking-lot-management-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── park/
│   │   │       ├── DB_Connection.java
│   │   │       ├── Main.java
│   │   │       ├── model/
│   │   │       │   ├── ParkingEntry.java
│   │   │       │   ├── ParkingLot.java
│   │   │       │   ├── Slot.java
│   │   │       │   └── Vehicle.java
│   │   │       └── service/
│   │   │           ├── BillingService.java
│   │   │           ├── CleanupData.java
│   │   │           └── ParkingService.java
│   │   └── resources/
│   │       └── Schema.sql
│   └── test/
│       └── java/
│           └── park/
│               └── ParkingSystemTest.java
├── .gitignore
├── pom.xml
├── LICENSE
└── README.md



---

🧱 Prerequisites

Before running the application, ensure you have:

Java 17 or higher

Maven

MySQL (or any JDBC-compatible database)

📦 Database Setup

Create a database (e.g. parking_lot_db) in MySQL.

Import the schema:

mysql -u <username> -p < src/main/resources/Schema.sql


Update database credentials in DB_Connection.java:

private static final String URL = "jdbc:mysql://localhost:3306/your_db_name";
private static final String USER = "your_username";
private static final String PASS = "your_password";


⚠️ Note: These are placeholders. Never commit real credentials.

▶️ How to Run

Build the project (from project root):

    mvn clean compile


Run the application:

    mvn exec:java -Dexec.mainClass="park.Main"


Follow console prompts to:

*Register vehicle entry

Allocate parking slots

Calculate parking fees

Exit vehicles and free slots
*
🧪 Running Tests

Execute all unit tests:

    *mvn test*


JUnit tests validate:

1.Slot allocation logic

2.Billing calculation

3.Entry/exit workflows

📝 Features
*
Vehicle entry and exit handling

Parking slot allocation

Billing logic based on usage

Modular and readable architecture

JDBC-based persistence

Unit testing with JUnit
*
⚠️ Limitations

Console-based (no UI)

Basic error handling

Single-user execution

Designed for learning purposes

📚 Learning Outcomes

Java OOP design

JDBC database connectivity

Maven project management

Unit testing with JUnit

GitHub repository structuring

📄 License

This project is licensed under the MIT License.