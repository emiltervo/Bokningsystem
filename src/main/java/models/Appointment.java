package models;

/**
 * Appointment class representing an appointment between a patient and a doctor.
 */
public class Appointment {
    private String startTime;
    private String date;
    private long patuserID;
    private long docuserID;
    private long lengthMinutes;
    private long room;

    /**
     * Constructor to create an Appointment object.
     *
     * @param startTime the start time of the appointment
     * @param date the date of the appointment
     * @param patuserID the user ID of the patient
     * @param docuserID the user ID of the doctor
     * @param lengthMinutes the length of the appointment in minutes
     * @param room the room number for the appointment
     */
    public Appointment(String startTime, String date, long patuserID, long docuserID, long lengthMinutes, long room) {
        this.startTime = startTime;
        this.date = date;
        this.patuserID = patuserID;
        this.docuserID = docuserID;
        this.lengthMinutes = lengthMinutes;
        this.room = room;
    }

    /**
     * Gets the start time of the appointment.
     *
     * @return the start time of the appointment
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the appointment.
     *
     * @param startTime the start time to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the date of the appointment.
     *
     * @return the date of the appointment
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the appointment.
     *
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the user ID of the patient.
     *
     * @return the user ID of the patient
     */
    public long getPatuserID() {
        return patuserID;
    }

    /**
     * Sets the user ID of the patient.
     *
     * @param patuserID the user ID to set
     */
    public void setPatuserID(long patuserID) {
        this.patuserID = patuserID;
    }

    /**
     * Gets the user ID of the doctor.
     *
     * @return the user ID of the doctor
     */
    public long getDocuserID() {
        return docuserID;
    }

    /**
     * Sets the user ID of the doctor.
     *
     * @param docuserID the user ID to set
     */
    public void setDocuserID(long docuserID) {
        this.docuserID = docuserID;
    }

    /**
     * Gets the length of the appointment in minutes.
     *
     * @return the length of the appointment in minutes
     */
    public long getLengthMinutes() {
        return lengthMinutes;
    }

    /**
     * Sets the length of the appointment in minutes.
     *
     * @param lengthMinutes the length in minutes to set
     */
    public void setLengthMinutes(long lengthMinutes) {
        this.lengthMinutes = lengthMinutes;
    }

    /**
     * Gets the room number for the appointment.
     *
     * @return the room number for the appointment
     */
    public long getRoom() {
        return room;
    }

    /**
     * Sets the room number for the appointment.
     *
     * @param room the room number to set
     */
    public void setRoom(long room) {
        this.room = room;
    }

    /**
     * Returns a string representation of the appointment.
     *
     * @return a string representation of the appointment
     */
    @Override
    public String toString() {
        return "[" + startTime + " " + date + " " + patuserID + " " + docuserID + " " + lengthMinutes + " " + room + "]";
    }
}