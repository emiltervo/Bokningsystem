package Calender;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private final List<Appointment> appointments;

    public Schedule() {
        this.appointments = new ArrayList<>();
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

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
            if (appointment.getDate().equals(date) && appointment.getTime().equals(time)) {
                return appointment;
            }
        }
        return null;
    }
}