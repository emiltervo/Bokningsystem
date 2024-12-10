package models;
import java.sql.*;
import java.util.ArrayList;
/** * Repository class for User objects. */
public class UserRepository {
    static ArrayList<User> userList = new ArrayList<>();
    /** Returns all users from the database */
    public static void getAllUsers() {
        // ArrayList<User> userList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT userID, name, password, email, role FROM users";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (userList != null) {
                userList.clear();
            }

            while (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");




                // Create specific objects based on role
                switch (role.toLowerCase()) {
                    case "doctor":
                        userList.add(new Doctor(userID, name, password, email, role));
                        break;
                    case "patient":
                        userList.add(new Patient(userID, name, password, email, role));
                        break;
                    case "secretary":
                        userList.add(new Secretary(userID, name, password, email, role));
                        break;
                    default:
                        System.out.println("Unknown role: " + role);
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User list:");
        System.out.println(userList);
    }

    /** Returns a specific user from the database */
    public static User getUserByID(int userID) {
        for (User user : userList) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null; // Return null if no user is found with the given userID
    }

    public static void addUser(ArrayList<String> userParams) {
        int userID = Integer.parseInt(userParams.get(0));
        String name = userParams.get(1);
        String password = userParams.get(2);
        String email = userParams.get(3);
        String role = userParams.get(4);

        // Create specific user object based on role
        User newUser;
        switch (role.toLowerCase()) {
            case "doctor":
                newUser = new Doctor(userID, name, password, email, role);
                break;
            case "patient":
                newUser = new Patient(userID, name, password, email, role);
                break;
            case "secretary":
                newUser = new Secretary(userID, name, password, email, role);
                break;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }

        // Add user to the userList
        userList.add(newUser);

        // Insert user into the database
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO users (userID, name, password, email, role) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userID);
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
                pstmt.setInt(1, userID);
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


}
