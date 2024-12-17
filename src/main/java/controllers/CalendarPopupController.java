package controllers;

import models.*;
import views.CalendarPopupView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CalendarPopupController {
    private final CalendarPopupView view;
    private final String doctor;
    private final Date slotDate;
    private final List<Patient> patients;

    public CalendarPopupController(CalendarPopupView view, String doctor, Date slotDate, List<Patient> patients) {
        this.view = view;
        this.doctor = doctor;
        this.slotDate = slotDate;
        this.patients = patients;
    }

    public void makeAppointment() {
        Doctor doctor = findDoctorByName(this.doctor);
        Patient patient = view.getSelectedPatient();
        assert patient != null;

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = outputFormat.format(slotDate);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String startTime = timeFormat.format(slotDate);

        Appointment appointment = AppointmentFactory.createAppointment(startTime, formattedDate, patient.getUserID(), doctor.getUserID(), 60, 1);
        AppointmentRepository.insertAppointment(appointment);
    }

    public void unBookPatient() {
        Doctor doctor = findDoctorByName(this.doctor);
        Patient patient = view.getSelectedPatient();
        assert patient != null;

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = outputFormat.format(slotDate);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String startTime = timeFormat.format(slotDate);

        AppointmentRepository.deleteAppointment(startTime, formattedDate, patient.getUserID(), doctor.getUserID());
    }

    private Doctor findDoctorByName(String doctorName) {
        List<Doctor> doctors = UserRepository.getDoctorList();
        for (Doctor d : doctors) {
            if (d.getName().equals(doctorName)) {
                return d;
            }
        }
        throw new IllegalStateException("Doctor not found");
    }
}