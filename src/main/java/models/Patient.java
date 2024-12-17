package models;

public class Patient extends User {
    public Patient(long userID, String name, String password, String email, String role) {
        super(userID, name, password, email, role);
    }
}