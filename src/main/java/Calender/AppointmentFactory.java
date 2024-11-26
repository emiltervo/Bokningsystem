package Calender;

public class AppointmentFactory {
    public static Appointment createAppointment(String description, String date, String time) {
        return new Appointment(description, date, time);
    }
}