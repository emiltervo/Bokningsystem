package models;

import java.sql.*;
import java.util.ArrayList;

/**
 * The UserRepository class provides methods to interact with the user database, including retrieving, adding, and managing users.
 */
public class UserRepository {
    private static ArrayList<User> userList = new ArrayList<>();
    private static ArrayList<Patient> patientList = new ArrayList<>();
    private static ArrayList<Doctor> doctorList = new ArrayList<>();
    private static ArrayList<Secretary> secretaryList = new ArrayList<>();

    /**
     * Retrieves all users from the database and populates the user lists.
     */
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

    /**
     * Retrieves a specific user from the database by their user ID.
     *
     * @param userID the ID of the user to retrieve
     * @return the User object if found, or null if not found
     */
    public static User getUserByID(long userID) {
        for (User user : userList) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null; // Return null if no user is found with the given userID
    }

    /**
     * Adds a new user to the database and the user list.
     *
     * @param userID the ID of the new user
     * @param name the name of the new user
     * @param password the password of the new user
     * @param email the email of the new user
     * @param role the role of the new user
     */
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

    /**
     * Retrieves the list of all users.
     *
     * @return an ArrayList of User objects
     */
    public static ArrayList<User> getUserList() {
        return userList;
    }

    /**
     * Retrieves the list of all patients.
     *
     * @return an ArrayList of Patient objects
     */
    public static ArrayList<Patient> getPatientList() {
        if (patientList.isEmpty()) {
            getAllUsers(); // Ensure the list is populated
        }
        return patientList;
    }

    /**
     * Retrieves the list of all doctors.
     *
     * @return an ArrayList of Doctor objects
     */
    public static ArrayList<Doctor> getDoctorList() {
        if (doctorList.isEmpty()) {
            getAllUsers(); // Ensure the list is populated
        }
        return doctorList;
    }

    /**
     * Retrieves the list of all secretaries.
     *
     * @return an ArrayList of Secretary objects
     */
    public static ArrayList<Secretary> getSecretaryList() {
        if (secretaryList.isEmpty()) {
            getAllUsers(); // Ensure the list is populated
        }
        return secretaryList;
    }
}