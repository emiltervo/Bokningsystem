package models;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Handles database connection.
 */
public class DatabaseConnection {
    private static Connection connection;

    /**
     * Gets the database connection. If the connection is not established or is closed, it initializes a new connection
     * using the credentials from the `dbconfig.properties` file.
     *
     * @return the database connection
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            Properties props = new Properties();
            try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("dbconfig.properties")) {
                if (input == null) {
                    System.out.println("Sorry, unable to find dbconfig.properties");
                    return null;
                }
                props.load(input);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            connection = DriverManager.getConnection(url, username, password);
        }

        return connection;
    }
}