import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract public class User implements AuthInterface {
    // userID is personal number
    private int userID;
    private String name;
    private String password;
    private String email;
    private String role;


    public User(int userID, String name, String password, String email, String role) {
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;

    }

    @Override
    public boolean login(int userID, String password) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String dbPassword = "poppy";

        String sql = "SELECT * FROM users WHERE userID = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userID);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                this.userID = rs.getInt("userID");
                this.name = rs.getString("name");
                this.password = rs.getString("password");
                this.email = rs.getString("email");
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }



    private void setUserID(int value) {
        userID = value;
    }
    
    private void setName (String input) {
        name = input;
    }
    private void setPassword(String input) {
        password = input;
    }
    private void setEmail(String input) {
        email = input;
    }
    private int getUserID() {
        return userID;
    }
    private String getName() {
        return name;
    }
    private String getPassword() {
        return password;
    }
    private String getEmail() {
        return email;
    }

}
