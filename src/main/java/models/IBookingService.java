package models;

public interface IBookingService<T> {
    boolean bookAppointment(T appointment);
    boolean cancelAppointment(T appointment);
    Schedule getSchedule();
}