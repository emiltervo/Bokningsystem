# Bokningsystem

Bokningsystem is a booking system designed to manage patient appointments at a medical facility. It allows Secretary to book and cancel appointments for patients. The Doctor can view their appointments and the patients' details.

## Features

- **User Authentication**: Secure login system for users.
- **Booking Management**: Book and cancel patient appointments with ease.
- **Data Persistence**: Save and retrieve booking and facility data between sessions using SQL files.
- **Validation**: Ensure data integrity through comprehensive input validation.

## Technical Specifications

- **Programming Language**: Java
- **Paradigm**: Object-Oriented Programming
- **Data Storage**: PostgresSQL
- **Frameworks and Libraries**: Swing, JUnit

## Installation Requirements
1. **Java Development Kit (JDK)**: Install the latest version of the JDK from the [official website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
2. **Git**: Install Git from the [official website](https://git-scm.com/downloads).
3. **PostgresSQL**: Install PostgresSQL from the [official website](https://www.postgresql.org/download/). To view the database, you can use a GUI tool like pgAdmin or DBeaver.
4. **IDE**: Install an Integrated Development Environment (IDE) such as Eclipse, IntelliJ IDEA, or NetBeans.

## Database Setup
1. **For Docker users**:
    - Run the following command in your terminal to start a PostgresSQL container.
    ```bash
    docker build -t postgres-with-init .
    docker run -d -p 5432:5432 --name postgres-container postgres-with-init
    ```
    - Connect to the database using:
    ```text
     Host: localhost
     Port: 5432
     Database: postgres
     Username: postgres
     Password: postgres
    ```
2. **For non-Docker users**:
    - Create a new database named `postgres` in PostgresSQL.
    - Update the database connection details in the `dbconfig.properties` class.
    - Run the database by running this file `database.sql`.
    - Use the same connection details as above in IntelliJ or your app.

## Setup
1. **Clone the repository**: Run the following command in your terminal.
```bash
git clone https://github.com/emiltervo/Bokningsystem.git
```
2. **Open the project**: Open the project in your IDE.
3. **Set up the database**: Create a new database in PostgresSQL. Update the database connection details in the `dbconfig.properties` class.
4. **Build the database**: Run the database by running this file `database.sql`.
5. **Run the application**: Run the `Main` class to start the application.
6. **Login**: Use the following credentials to log in.
   - Secretary
     - Username: 198707912345
     - Password: patientPass93
   - Doctor
     - Username: 197607182345
     - Password: patientPass6
   - Patient
     - Username: 198008056789
     - Password: patientPass19
6. **Enjoy!**: You can now use the booking system.


