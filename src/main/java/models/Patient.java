package models;

import java.util.ArrayList;
import java.util.List;
/** Patient class that extends User */
// TODO: Journal is not used, will try adding it by end of Sunday (15/12/2024) (David)
public class Patient extends User {
    private String journal;
    private List<Prescription> prescriptions;

    public Patient(long userID, String name, String password, String email, String role/*, String journal*/) {
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

    @Override
    public String toString() {
        return this.getName() + " (" + this.getUserID() + ")";
    }
}