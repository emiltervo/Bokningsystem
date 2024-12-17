package models;

/**
 * Represents a patient in the system.
 * Extends the User class.
 */
public class Patient extends User {

    /**
     * Constructs a new Patient object with the specified parameters.
     *
     * @param userID the unique identifier for the patient
     * @param name the name of the patient
     * @param password the password for the patient's account
     * @param email the email address of the patient
     * @param role the role of the patient (e.g., "patient")
     */
    public Patient(long userID, String name, String password, String email, String role) {
        super(userID, name, password, email, role);
    }
}