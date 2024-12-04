package models;

import java.util.ArrayList;
import java.util.List;

public class Patient extends User {
    private String journal;
    private List<Prescription> prescriptions;

    public Patient(int userID, String name, String password, String email, String role, String journal) {
        super(userID, name, password, email, role);
        //this.journal = journal;
        this.prescriptions = new ArrayList<>();
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        this.prescriptions.add(prescription);
    }

    public void removePrescription(Prescription prescription) {
        this.prescriptions.remove(prescription);
    }
}