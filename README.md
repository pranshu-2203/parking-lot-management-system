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


```
---

```md
## 🗄️ Database Schema (Overview)

The system uses a relational database to persist parking data.

**Core Tables:**

- **parking_lot**
  - id
  - total_slots
  - available_slots

- **parking_entry**
  - entry_id
  - vehicle_number
  - vehicle_type
  - slot_number
  - entry_time
  - exit_time
  - total_fee

**Relationships:**
- One parking lot → multiple parking entries
- Each parking entry is associated with a single slot

The schema is defined in:
`src/main/resources/Schema.sql`
```
---
🧱 Prerequisites

Make sure you have the following installed:

Java 17 or higher

Maven

MySQL (or any JDBC-compatible database)
---
📦 Database Setup

Create a database (example: parking_lot_db) in MySQL.

Import the schema:

mysql -u <username> -p < src/main/resources/Schema.sql


Update database credentials in DB_Connection.java:

private static final String URL = "jdbc:mysql://localhost:3306/your_db_name";
private static final String USER = "your_username";
private static final String PASS = "your_password";


⚠️ Important:
These are placeholders. Never commit real credentials to GitHub.
---
▶️ How to Run

From the project root directory:

Build the project
mvn clean compile

Run the application
mvn exec:java -Dexec.mainClass="park.Main"

Follow the console prompts to:

Register vehicle entry

Allocate parking slots

Calculate parking fees

Exit vehicles and free slots
---
## 🖥️ Sample Console Output

```text
====== Parking Lot Management System ======

1. Park Vehicle
2. Exit Vehicle
3. View Parking Status
4. Exit Application

Enter your choice: 1

Enter Vehicle Number: DL01AB1234
Enter Vehicle Type (CAR/BIKE): CAR

Slot allocated successfully.
Assigned Slot Number: 3
Entry Time: 2026-02-04 18:32:10
Enter your choice: 2

Enter Vehicle Number: DL01AB1234

Vehicle exited successfully.
Parking Duration: 2 hours
Total Fee: ₹100
Slot freed successfully.
```

---

🧪 Running Tests

Run all unit tests using:

mvn test


JUnit tests validate:

Slot allocation logic

Billing calculations

Vehicle entry and exit workflows

📝 Features

Vehicle entry and exit handling

Parking slot allocation logic

Billing based on parking usage

Modular and readable architecture

JDBC-based database persistence

Unit testing with JUnit

⚠️ Limitations

Console-based (no graphical UI)

Basic error handling

Single-user execution

Designed for learning and demonstration purposes

📚 Learning Outcomes

Java OOP and modular design

JDBC database connectivity

Maven project structure and lifecycle

Writing and running unit tests

Maintaining a clean GitHub repository

📄 License

This project is licensed under the MIT License.
