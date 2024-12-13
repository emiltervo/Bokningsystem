package models;

import java.util.Date;

/** Prescription class that contains medication name, dosage, start date and end date */

public class Prescription {
    private String medicationName;
    private String dosage;
    private Date startDate;
    private Date endDate;

    public Prescription(String medicationName, String dosage, Date startDate, Date endDate) {
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "medicationName='" + medicationName + '\'' +
                ", dosage='" + dosage + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}