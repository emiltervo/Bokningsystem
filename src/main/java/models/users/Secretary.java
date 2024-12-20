package models.users;

/**
 * Secretary class that extends User.
 */
public class Secretary extends User {

    /**
     * Constructs a Secretary with the specified details.
     *
     * @param userID the ID of the secretary
     * @param name the name of the secretary
     * @param password the password of the secretary
     * @param email the email of the secretary
     * @param role the role of the secretary
     */
    public Secretary(long userID, String name, String password, String email, String role) {
        super(userID, name, password, email, role);
    }
}