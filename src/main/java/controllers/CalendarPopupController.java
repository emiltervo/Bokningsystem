package controllers;

import models.*;
import views.CalendarPopupView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The CalendarPopupController class manages the interactions between the CalendarPopupView and the underlying data models.
 * It handles the creation and deletion of appointments.
 */
public class CalendarPopupController {
    private final CalendarPopupView view;
    private final String doctor;
    private final Date slotDate;
    private final List<Patient> patients;

    /**
     * Constructs a CalendarPopupController with the specified view, doctor, slot date, and list of patients.
     *
     * @param view the CalendarPopupView to be managed by this controller
     * @param doctor the name of the doctor
     * @param slotDate the date and time of the slot
     * @param patients the list of patients
     */
    public CalendarPopupController(CalendarPopupView view, String doctor, Date slotDate, List<Patient> patients) {
        this.view = view;
        this.doctor = doctor;
        this.slotDate = slotDate;
        this.patients = patients;
    }

    /**
     * Creates an appointment for the selected patient and doctor at the specified slot date and time.
     */
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

    /**
     * Cancels the appointment for the selected patient and doctor at the specified slot date and time.
     */
    public void unBookPatient() {
        Doctor doctor = findDoctorByName(this.doctor);
        Patient patient = view.getSelectedPatient();
        assert patient != null;

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = outputFormat.format(slotDate);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String startTime = timeFormat.format(slotDate);
        // print the appointment details
        System.out.println("Appointment details: " + startTime + " " + formattedDate + " " + patient.getUserID() + " " + doctor.getUserID());
        AppointmentRepository.deleteAppointment(startTime, formattedDate, patient.getUserID(), doctor.getUserID());
    }

    /**
     * Finds a doctor by their name.
     *
     * @param doctorName the name of the doctor
     * @return the Doctor object
     * @throws IllegalStateException if the doctor is not found
     */
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