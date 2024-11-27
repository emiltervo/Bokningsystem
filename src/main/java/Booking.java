import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Booking {
    private int id;
    private Date date;
    private Time time;
    private int doctorId;
    private int userId;
    private int secretaryId;
    private Timestamp createdAt;

    // Constructors, getters, and setters
    public Booking(int id, Date date, Time time, int doctorId, int userId, int secretaryId, Timestamp createdAt) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.doctorId = doctorId;
        this.userId = userId;
        this.secretaryId = secretaryId;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSecretaryId() {
        return secretaryId;
    }

    public void setSecretaryId(int secretaryId) {
        this.secretaryId = secretaryId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
