package models;

import java.sql.*;
import java.util.ArrayList;

public class UserRepository {
    static ArrayList<User> userList = new ArrayList<>();
    static ArrayList<Patient> patientList = new ArrayList<>();
    static ArrayList<Doctor> doctorList = new ArrayList<>();
    static ArrayList<Secretary> secretaryList = new ArrayList<>();

    /** Returns all users from the database */
    public static void getAllUsers() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT userID, name, password, email, role FROM users";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (userList != null) {
                userList.clear();
            }
            if (patientList != null) {
                patientList.clear();
            }
            if (doctorList != null) {
                doctorList.clear();
            }
            if (secretaryList != null) {
                secretaryList.clear();
            }

            while (resultSet.next()) {
                long userID = resultSet.getLong("userID");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");

                User user = UserFactory.createUser(userID, name, password, email, role);
                userList.add(user);
                if (user instanceof Patient) {
                    patientList.add((Patient) user);
                }
                if (user instanceof Doctor) {
                    doctorList.add((Doctor) user);
                }
                if (user instanceof Secretary) {
                    secretaryList.add((Secretary) user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User list:");
        System.out.println(userList);
    }



    /** Returns a specific user from the database */
    public static User getUserByID(long userID) {
        for (User user : userList) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null; // Return null if no user is found with the given userID
    }

    /** Adds a new user to the database and userList */
    public static void addUser(long userID, String name, String password, String email, String role) {

        User newUser = UserFactory.createUser(userID, name, password, email, role);

        // Add user to the userList
        userList.add(newUser);
        if (newUser instanceof Patient) {
            patientList.add((Patient) newUser);
        }
        if (newUser instanceof Doctor) {
            doctorList.add((Doctor) newUser);
        }
        if (newUser instanceof Secretary) {
            secretaryList.add((Secretary) newUser);
        }

        // Insert user into the database
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO users (userID, name, password, email, role) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1, userID);
                pstmt.setString(2, name);
                pstmt.setString(3, password);
                pstmt.setString(4, email);
                pstmt.setString(5, role);
                pstmt.executeUpdate();
            }

            // Insert user into the specific role table
            String roleTable = role.toLowerCase();
            sql = "INSERT INTO " + roleTable + " (userID, name, password, email, role) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1, userID);
                pstmt.setString(2, name);
                pstmt.setString(3, password);
                pstmt.setString(4, email);
                pstmt.setString(5, role);
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static ArrayList<Patient> getPatientList(){
        return patientList;
    }

    public static ArrayList<Doctor> getDoctorList(){
        return doctorList;
    }

    public static ArrayList<Secretary> getSecretaryList(){
        return secretaryList;
    }
}