package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            String url = "jdbc:postgresql://localhost:5433/postgres";
            String username = "postgres";
            String password = "postgres";
            connection = DriverManager.getConnection(url, username, password);
        }

        return connection;
    }
}
