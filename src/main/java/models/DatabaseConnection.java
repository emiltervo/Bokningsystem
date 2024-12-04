package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5433/postgres";
        String username = "postgres";
        String password = "postgres";
        return DriverManager.getConnection(url, username, password);
    }
}
