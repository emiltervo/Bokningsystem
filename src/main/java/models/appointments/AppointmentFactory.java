package models.appointments;


/**
 * Factory class for creating Appointment objects.
 */
public class AppointmentFactory {

    /**
     * Creates an Appointment object with the specified parameters.
     *
     * @param startTime the start time of the appointment
     * @param date the date of the appointment
     * @param patuserID the user ID of the patient
     * @param docuserID the user ID of the doctor
     * @param lengthMinutes the length of the appointment in minutes
     * @param room the room number for the appointment
     * @return a new Appointment object
     */
    public static Appointment createAppointment(String startTime, String date, long patuserID, long docuserID, long lengthMinutes, long room) {
        return new Appointment(startTime, date, patuserID, docuserID, lengthMinutes, room);
    }
}