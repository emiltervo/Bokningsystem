CREATE TABLE users (
    userID CHAR (10) PRIMARY KEY,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL,
    role TEXT NOT NULL
);
CREATE TABLE doctor (
    userID CHAR (10) PRIMARY KEY,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL,
    FOREIGN KEY (userID) REFERENCES users(userID)
);
CREATE TABLE patient (
    userID CHAR (10) PRIMARY KEY,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL,
    FOREIGN KEY (userID) REFERENCES users(userID)
);
CREATE TABLE rooms(
    roomNumber INT PRIMARY KEY,
    roomDoctor CHAR(10) NOT NULL,
    FOREIGN KEY (roomDoctor) REFERENCES doctor(userID)
);
CREATE TABLE appointments(

    date TEXT,
    patuserID CHAR(10),
    docuserID CHAR(10),
    lengthMinutes INT,
    room CHAR(6)
    FOREIGN KEY(patuserID) REFERENCES patient(userID),
    FOREIGN KEY(docuserID) REFERENCES doctor(userID)

);



CREATE VIEW showDoctor AS
SELECT *
FROM users
WHERE role = 'doctor';

CREATE VIEW showPatient AS
SELECT *
FROM users
WHERE role = 'patient';

CREATE VIEW showSecretary AS
SELECT *
FROM users
WHERE role = 'secretary';

