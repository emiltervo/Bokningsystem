package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentFactory {
    public static Appointment createAppointment(String startTime, String date, int patuserID, int docuserID, int lengthMinutes, int room) {
        return new Appointment(startTime, date, patuserID, docuserID, lengthMinutes, room);
    }

    public static void insertAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (startTime, date, patuserID, docuserID, lengthMinutes, room) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, appointment.getStartTime());
            pstmt.setString(2, appointment.getDate());
            pstmt.setInt(3, appointment.getPatuserID());
            pstmt.setInt(4, appointment.getDocuserID());
            pstmt.setInt(5, appointment.getLengthMinutes());
            pstmt.setInt(6, appointment.getRoom());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Appointment> getAllAppointments() {
        ArrayList<Appointment> appointmentList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT startTime, date, patuserID, docuserID, lengthMinutes, room FROM appointments";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String startTime = resultSet.getString("startTime");
                String date = resultSet.getString("date");
                int patuserID = resultSet.getInt("patuserID");
                int docuserID = resultSet.getInt("docuserID");
                int lengthMinutes = resultSet.getInt("lengthminutes");
                int room = resultSet.getInt("room");


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