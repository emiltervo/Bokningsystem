package models;

public class AppointmentFactory {
    public static Appointment createAppointment(String startTime, String date, int patuserID, int docuserID, int lengthMinutes, int room) {
        return new Appointment(startTime, date, patuserID, docuserID, lengthMinutes, room);
    }
}