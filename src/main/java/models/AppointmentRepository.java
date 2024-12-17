package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The AppointmentRepository class provides methods to interact with the appointments database.
 */
public class AppointmentRepository {

    /**
     * Inserts an appointment into the database.
     *
     * @param appointment the appointment to be inserted
     */
    public static void insertAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (startTime, date, patuserID, docuserID, lengthMinutes, room) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, appointment.getStartTime());
            pstmt.setString(2, appointment.getDate());
            pstmt.setLong(3, appointment.getPatuserID());
            pstmt.setLong(4, appointment.getDocuserID());
            pstmt.setLong(5, appointment.getLengthMinutes());
            pstmt.setLong(6, appointment.getRoom());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all appointments from the database.
     *
     * @return a list of all appointments
     */
    public static ArrayList<Appointment> getAllAppointments() {
        ArrayList<Appointment> appointmentList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT startTime, date, patuserID, docuserID, lengthMinutes, room FROM appointments";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String startTime = resultSet.getString("startTime");
                String date = resultSet.getString("date");
                long patuserID = resultSet.getLong("patuserID");
                long docuserID = resultSet.getLong("docuserID");
                long lengthMinutes = resultSet.getLong("lengthminutes");
                long room = resultSet.getLong("room");

                appointmentList.add(AppointmentFactory.createAppointment(startTime, date, patuserID, docuserID, lengthMinutes, room));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentList;
    }

    /**
     * Retrieves all appointments for a specific user from the database.
     *
     * @param userID the user ID
     * @return a list of appointments for the specified user
     */
    public static ArrayList<Appointment> getAllAppointmentsByUserID(long userID) {
        ArrayList<Appointment> appointmentListByUserID = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT startTime, date, patuserID, docuserID, lengthMinutes, room FROM appointments WHERE patuserID = ? OR docuserID = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, userID);
            statement.setLong(2, userID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String startTime = resultSet.getString("startTime");
                String date = resultSet.getString("date");
                long patuserID = resultSet.getLong("patuserID");
                long docuserID = resultSet.getLong("docuserID");
                long lengthMinutes = resultSet.getLong("lengthMinutes");
                long room = resultSet.getLong("room");

                appointmentListByUserID.add(AppointmentFactory.createAppointment(startTime, date, patuserID, docuserID, lengthMinutes, room));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentListByUserID;
    }

    /**
     * Deletes an appointment from the database.
     *
     * @param startTime the start time of the appointment
     * @param formattedDate the date of the appointment
     * @param userID the patient user ID
     * @param userID1 the doctor user ID
     */
    public static void deleteAppointment(String startTime, String formattedDate, long userID, long userID1) {
        String sql = "DELETE FROM appointments WHERE startTime = ? AND date = ? AND patuserID = ? AND docuserID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, startTime);
            pstmt.setString(2, formattedDate);
            pstmt.setLong(3, userID);
            pstmt.setLong(4, userID1);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a specific appointment from the database.
     *
     * @param startTime the start time of the appointment
     * @param formattedDate the date of the appointment
     * @param userID the patient user ID
     * @param userID1 the doctor user ID
     * @return the appointment if found, otherwise null
     */
    public static Appointment getAppointment(String startTime, String formattedDate, long userID, long userID1) {
        Appointment appointment = null;
        String sql = "SELECT startTime, date, patuserID, docuserID, lengthMinutes, room FROM appointments WHERE startTime = ? AND date = ? AND patuserID = ? AND docuserID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, startTime);
            pstmt.setString(2, formattedDate);
            pstmt.setLong(3, userID);
            pstmt.setLong(4, userID1);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                String startTime1 = resultSet.getString("startTime");
                String date = resultSet.getString("date");
                long patuserID = resultSet.getLong("patuserID");
                long docuserID = resultSet.getLong("docuserID");
                long lengthMinutes = resultSet.getLong("lengthMinutes");
                long room = resultSet.getLong("room");

                appointment = AppointmentFactory.createAppointment(startTime1, date, patuserID, docuserID, lengthMinutes, room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }

    /**
     * Deletes a specific appointment from the database.
     *
     * @param appointment the appointment to be deleted
     */
    public static void deleteAppointment(Appointment appointment) {
        String sql = "DELETE FROM appointments WHERE startTime = ? AND date = ? AND patuserID = ? AND docuserID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, appointment.getStartTime());
            pstmt.setString(2, appointment.getDate());
            pstmt.setLong(3, appointment.getPatuserID());
            pstmt.setLong(4, appointment.getDocuserID());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}