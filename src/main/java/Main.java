import Calender.Appointment;
import Calender.Schedule;
import Calender.BookingService;
import Calender.AppointmentFactory;

public class Main {
    public static void main(String[] args) {
        // Create an appointment
        Appointment appointment = AppointmentFactory.createAppointment("Meeting with John", "2021-12-25", "10:00 AM");
        BookingService bookingService = BookingService.getInstance();
        bookingService.addAppointment(appointment);
        System.out.println(bookingService.getSchedule());
    }
}
