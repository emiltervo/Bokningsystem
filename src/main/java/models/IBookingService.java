package models;

/** Interface for booking service */

public interface IBookingService<T> {
    boolean bookAppointment(T appointment);
    boolean cancelAppointment(T appointment);
    Schedule getSchedule();
}