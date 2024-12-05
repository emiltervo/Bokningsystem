package models;
import java.sql.*;
import java.util.ArrayList;

public class UserRepository {
    public static ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT userID, name, password, email, role FROM users";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

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
        return userList;
    }
}
