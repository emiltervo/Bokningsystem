package models;

/** Appointment class */

public class Appointment {
    private String startTime;
    private String date;
    private long patuserID;
    private long docuserID;
    private long lengthMinutes;
    private long room;

    public Appointment(String startTime, String date, long patuserID, long docuserID, long lengthMinutes, long room) {
        this.startTime = startTime;
        this.date = date;
        this.patuserID = patuserID;
        this.docuserID = docuserID;
        this.lengthMinutes = lengthMinutes;
        this.room = room;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getPatuserID() {
        return patuserID;
    }

    public void setPatuserID(long patuserID) {
        this.patuserID = patuserID;
    }

    public long getDocuserID() {
        return docuserID;
    }

    public void setDocuserID(long docuserID) {
        this.docuserID = docuserID;
    }

    public long getLengthMinutes() {
        return lengthMinutes;
    }

    public void setLengthMinutes(int lengthMinutes) {
        this.lengthMinutes = lengthMinutes;
    }

    public long getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "[" + startTime + " " + date + " " + patuserID + " " + docuserID + " " + lengthMinutes + " " + room +"]";
    }
}
