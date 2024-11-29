package Calender;

import org.junit.jupiter.api.Test;

public class ScheduleTest {
   @Test
    public void testAddAppointment() {
        Schedule schedule = new Schedule();
        Appointment appointment = AppointmentFactory.createAppointment("Dentist", "2021-12-01", "10:00");
        schedule.addAppointment(appointment);
        assert schedule.getAppointments().size() == 1;
    }
    @Test
    public void testRemoveAppointment() {
        Schedule schedule = new Schedule();
        Appointment appointment = AppointmentFactory.createAppointment("Dentist", "2021-12-01", "10:00");
        schedule.addAppointment(appointment);
        schedule.removeAppointment(appointment);
        assert schedule.getAppointments().isEmpty();
    }
    @Test
    public void testGetAppointments() {
        Schedule schedule = new Schedule();
        Appointment appointment = AppointmentFactory.createAppointment("Dentist", "2021-12-01", "10:00");
        schedule.addAppointment(appointment);
        assert schedule.getAppointments().size() == 1;
    }
    @Test
    public void testGetAppointmentsByDate() {
        Schedule schedule = new Schedule();
        Appointment appointment = AppointmentFactory.createAppointment("Dentist", "2021-12-01", "10:00");
        schedule.addAppointment(appointment);
        assert schedule.getAppointmentsByDate("2021-12-01").size() == 1;
    }
    @Test
    public void testGetAppointmentsByDateAndTime() {
        Schedule schedule = new Schedule();
        Appointment appointment = AppointmentFactory.createAppointment("Dentist", "2021-12-01", "10:00");
        schedule.addAppointment(appointment);
        assert schedule.getAppointmentsByDateAndTime("2021-12-01", "10:00") != null;
    }
    @Test void testGetAppointmentsByDateAndTimeReturnsNull() {
        Schedule schedule = new Schedule();
        Appointment appointment = AppointmentFactory.createAppointment("Dentist", "2021-12-01", "10:00");
        schedule.addAppointment(appointment);
        assert schedule.getAppointmentsByDateAndTime("2021-12-01", "11:00") == null;
    }
}
