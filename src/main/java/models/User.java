package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Abstract superclass for users */

abstract public class User implements AuthInterface {
    // userID is personal number
    private long userID;
    private String name;
    private String password;
    private String email;
    private String role;

    // Constructor
    public User(long userID, String name, String password, String email, String role) {
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    /** Login functionality for users that conntects to the database */
    @Override
    public boolean login(long userID, String password) {
        String sql = "SELECT * FROM users WHERE userID = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, userID);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                this.userID = rs.getLong("userID");
                this.name = rs.getString("name");
                this.password = rs.getString("password");
                this.email = rs.getString("email");
                this.role = rs.getString("role");
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Setters
    public void setUserID(long value) {
        userID = value;
    }

    public void setName(String input) {
        name = input;
    }

    public void setPassword(String input) {
        password = input;
    }

    public void setEmail(String input) {
        email = input;
    }

    // Getters
    public long getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    // toString method for User
    @Override
    public String toString() {
        return "Username: " + userID + "\nName: " + name + "\nPassword: " + password + "\nEmail: " + email + "\nRole: " + role + "";
    }
}
