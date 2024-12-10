# Bokningsystem

Bokningsystem is a booking system designed to manage patient appointments at a medical facility. It allows Secretary to book and cancel appointments for patients. The Doctor can view their appointments and the patients' details.

## Features

- **User Authentication**: Secure login system for users.
- **Booking Management**: Book and cancel patient appointments with ease.
- **Data Persistence**: Save and retrieve booking and facility data between sessions using JSON files.
- **Validation**: Ensure data integrity through comprehensive input validation.

## Technical Specifications

- **Programming Language**: Java
- **Paradigm**: Object-Oriented Programming
- **Data Storage**: PostgresSQL
- **Frameworks and Libraries**: Swing, JUnit

## Installation

## Requirements
1. **Java Development Kit (JDK)**: Install the latest version of the JDK from the [official website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
2. **Git**: Install Git from the [official website](https://git-scm.com/downloads).
3. **PostgresSQL**: Install PostgresSQL from the [official website](https://www.postgresql.org/download/). To view the database, you can use a GUI tool like pgAdmin or DBeaver.
4. **IDE**: Install an Integrated Development Environment (IDE) such as Eclipse, IntelliJ IDEA, or NetBeans.

## Setup
1. **Clone the repository**: Run the following command in your terminal.
```bash
git clone https://github.com/emiltervo/Bokningsystem.git
```
2. **Open the project**: Open the project in your IDE.
3. **Set up the database**: Create a new database in PostgresSQL. Update the database connection details in the `DatabaseConnection` class.
4. **Run the application**: Run the `Main` class to start the application.
5. **Login**: Use the following credentials to log in.
   - Secretary
     - Username: secretary
     - Password: secretary
   - Doctor
     - Username: doctor
     - Password: doctor
   - Patient
     - Username: patient
     - Password: patient
6. **Enjoy!**: You can now use the booking system.


