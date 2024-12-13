package models;

/** Interface for authentication */

public interface AuthInterface {
    boolean login(long userID, String password);
}
