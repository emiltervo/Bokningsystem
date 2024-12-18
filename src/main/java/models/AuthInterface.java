package models;

/**
 * Interface for authentication.
 */
public interface AuthInterface {

    /**
     * Logs in a user with the specified user ID and password.
     *
     * @param userID the user ID of the user
     * @param password the password of the user
     * @return true if the login is successful, false otherwise
     */
    boolean login(long userID, String password);
}