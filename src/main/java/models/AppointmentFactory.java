package models;

public class AppointmentFactory {
    /** Creates an appointment object with Factory Pattern */
    public static Appointment createAppointment(String startTime, String date, long patuserID, long docuserID, long lengthMinutes, long room) {
        return new Appointment(startTime, date, patuserID, docuserID, lengthMinutes, room);
    }
}