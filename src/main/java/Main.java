import controllers.loginController;
import models.*;
import views.LoginView;


public class Main {
    public static void main(String[] args) {
        // Create an appointment
        Appointment appointment = AppointmentFactory.createAppointment("10:00", "2021-01-01", 1, 2, 30, 1);
        BookingService bookingService = BookingService.getInstance();
        bookingService.bookAppointment(appointment);
        UserRepository.getAllUsers();
        LoginView loginView = new LoginView();
        loginController loginController = new loginController(loginView);
        loginView.setController(loginController);
        loginView.createUI();
        AppointmentFactory.getAllAppointments();
        System.out.println(bookingService.getSchedule());
    }
}
