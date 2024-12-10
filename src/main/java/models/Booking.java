package models;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Booking {
    private long id;
    private Date date;
    private Time time;
    private long doctorId;
    private long userId;
    private long secretaryId;
    private Timestamp createdAt;

    // Constructors, getters, and setters
    public Booking(long id, Date date, Time time, long doctorId, long userId, long secretaryId, Timestamp createdAt) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.doctorId = doctorId;
        this.userId = userId;
        this.secretaryId = secretaryId;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSecretaryId() {
        return secretaryId;
    }

    public void setSecretaryId(long secretaryId) {
        this.secretaryId = secretaryId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
