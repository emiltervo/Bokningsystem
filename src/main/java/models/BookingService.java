package models;

public class BookingService implements IBookingService<Appointment> {
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

    @Override
    public boolean bookAppointment(Appointment appointment) {
        if (schedule.getAppointments().contains(appointment)) {
            return false;
        }
        schedule.addAppointment(appointment);
        return true;
    }

    @Override
    public boolean cancelAppointment(Appointment appointment) {
        if (!schedule.getAppointments().contains(appointment)) {
            return false;
        }
        schedule.removeAppointment(appointment);
        return true;
    }

    @Override
    public Schedule getSchedule() {
        return schedule;
    }
}