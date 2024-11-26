package Calender;

public class BookingService {
    private static BookingService instance;
    private Schedule schedule;

    private BookingService() {
        this.schedule = new Schedule();
    }

    public static synchronized BookingService getInstance() {
        if (instance == null) {
            instance = new BookingService();
        }
        return instance;
    }

    public void addAppointment(Appointment appointment) {
        schedule.addAppointment(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        schedule.removeAppointment(appointment);
    }

    public Schedule getSchedule() {
        return schedule;
    }
}