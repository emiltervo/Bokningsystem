package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentFactory {
    /** Creates an appointment object */
    public static Appointment createAppointment(String startTime, String date, long patuserID, long docuserID, long lengthMinutes, long room) {
        return new Appointment(startTime, date, patuserID, docuserID, lengthMinutes, room);
    }
    /** Inserts appointments into database based on values of the appointment object */
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
    /** Retrieves appointment information from the database and creates objects of type Appointments. */
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


                appointmentList.add(createAppointment(startTime, date, patuserID, docuserID, lengthMinutes, room));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Appointment list:");
        System.out.println(appointmentList);
        return appointmentList;
    }


}