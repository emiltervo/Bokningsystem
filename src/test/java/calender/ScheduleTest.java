package calender;


import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ScheduleTest {
    /*
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
    }*/

    @Test
    public void testDatabaseConnection() {
        String url = "jdbc:postgresql://localhost:5433/postgres";
        String user = "postgres";
        String dbPassword = "postgres";

        String sql = "SELECT * FROM users";

        try (Connection conn = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
