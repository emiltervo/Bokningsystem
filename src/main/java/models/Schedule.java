package models;

import java.util.ArrayList;
import java.util.List;

/** Schedule class that contains a list of appointments */

public class Schedule {
    private final List<Appointment> appointments;

    // Constructor
    public Schedule() {
        this.appointments = new ArrayList<>();
    }


    // Method for adding an appointment to the schedule
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    // Method for removing an appointment from the schedule

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    // Method for getting all appointments in the schedule

    public List<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Appointment appointment : appointments) {
            sb.append(appointment.toString()).append("\n");
        }
        return sb.toString();
    }

    public List<Appointment> getAppointmentsByDate(String date) {
        List<Appointment> appointmentsByDate = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date)) {
                appointmentsByDate.add(appointment);
            }
        }
        return appointmentsByDate;
    }

    public Appointment getAppointmentsByDateAndTime(String date, String time) {
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date) && appointment.getStartTime().equals(time)) {
                return appointment;
            }
        }
        return null;
    }



}