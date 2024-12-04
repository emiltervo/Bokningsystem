package models;

public class Appointment {
    private String startTime;
    private String date;
    private int patuserID;
    private int docuserID;
    private int lengthMinutes;
    private int room;

    public Appointment(String startTime, String date, int patuserID, int docuserID, int lengthMinutes, int room) {
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

    public int getPatuserID() {
        return patuserID;
    }

    public void setPatuserID(int patuserID) {
        this.patuserID = patuserID;
    }

    public int getDocuserID() {
        return docuserID;
    }

    public void setDocuserID(int docuserID) {
        this.docuserID = docuserID;
    }

    public int getLengthMinutes() {
        return lengthMinutes;
    }

    public void setLengthMinutes(int lengthMinutes) {
        this.lengthMinutes = lengthMinutes;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

  /*  @Override
    public String toString() {
        return "Appointment{" +
                "description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
 */
}
