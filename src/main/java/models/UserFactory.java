package models;

public class UserFactory {
    /** Creates a user object based on role using Factory Pattern */
    public static User createUser(long userID, String name, String password, String email, String role) {
        switch (role.toLowerCase()) {
            case "doctor":
                return new Doctor(userID, name, password, email, role);
            case "patient":
                return new Patient(userID, name, password, email, role);
            case "secretary":
                return new Secretary(userID, name, password, email, role);
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }
}
