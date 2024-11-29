import models.Appointment;
import models.BookingService;
import models.AppointmentFactory;

public class Main {
    public static void main(String[] args) {
        // Create an appointment
        Appointment appointment = AppointmentFactory.createAppointment("Meeting with John", "2021-12-25", "10:00 AM");
        BookingService bookingService = BookingService.getInstance();
        bookingService.bookAppointment(appointment);
        System.out.println(bookingService.getSchedule());
    }
}
