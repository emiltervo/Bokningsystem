import controllers.CalendarController;
import controllers.*;
import models.*;
import views.*;
import views.CalendarView;


public class Main {
    public static void main(String[] args) {
        // Create an appointment
        Appointment appointment = AppointmentFactory.createAppointment("10:00", "2021-01-01", 1, 2, 30, 1);
        BookingService bookingService = BookingService.getInstance();
        bookingService.bookAppointment(appointment);
        UserRepository.getAllUsers();
        CreateViews createViews = CreateViews.getInstance();
        LoginView loginView = createViews.getLoginView();
        loginView.setController(new loginController(loginView));
        loginView.createUI();
    }
}
