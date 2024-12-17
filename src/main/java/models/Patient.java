package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Patient class that extends User.
 */
public class Patient extends User {
    private String journal;
    private List<Prescription> prescriptions;

    /**
     * Constructor to create a Patient object.
     *
     * @param userID the user ID of the patient
     * @param name the name of the patient
     * @param password the password of the patient
     * @param email the email of the patient
     * @param role the role of the patient
     */
    public Patient(long userID, String name, String password, String email, String role) {
        super(userID, name, password, email, role);
        this.prescriptions = new ArrayList<>();
    }

    /**
     * Gets the journal of the patient.
     *
     * @return the journal of the patient
     */
    public String getJournal() {
        return journal;
    }

    /**
     * Sets the journal of the patient.
     *
     * @param journal the journal to set
     */
    public void setJournal(String journal) {
        this.journal = journal;
    }

    /**
     * Gets the list of prescriptions of the patient.
     *
     * @return the list of prescriptions
     */
    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    /**
     * Adds a prescription to the list of prescriptions.
     *
     * @param prescription the prescription to add
     */
    public void addPrescription(Prescription prescription) {
        this.prescriptions.add(prescription);
    }

    /**
     * Removes a prescription from the list of prescriptions.
     *
     * @param prescription the prescription to remove
     */
    public void removePrescription(Prescription prescription) {
        this.prescriptions.remove(prescription);
    }
}