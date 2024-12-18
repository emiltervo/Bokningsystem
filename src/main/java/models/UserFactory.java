package models;

/**
 * The UserFactory class provides a method to create User objects based on the specified role.
 */
public class UserFactory {

    /**
     * Creates a User object with the specified details.
     *
     * @param userID the ID of the user
     * @param name the name of the user
     * @param password the password of the user
     * @param email the email of the user
     * @param role the role of the user
     * @return a new User object based on the specified role
     */
    public static User createUser(long userID, String name, String password, String email, String role) {
        switch (role.toLowerCase()) {
            case "patient":
                return new Patient(userID, name, password, email, role);
            case "doctor":
                return new Doctor(userID, name, password, email, role);
            case "secretary":
                return new Secretary(userID, name, password, email, role);
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}