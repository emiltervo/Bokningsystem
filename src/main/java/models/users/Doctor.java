package models.users;

import java.util.List;
import java.util.ArrayList;

/**
 * Doctor class that extends User.
 */
public class Doctor extends User {
    private boolean availability;
    private String specialty;
    private List<String> freetimes;

    /**
     * Constructor to create a Doctor object.
     *
     * @param userID the user ID of the doctor
     * @param name the name of the doctor
     * @param password the password of the doctor
     * @param email the email of the doctor
     * @param role the role of the doctor
     */
    public Doctor(long userID, String name, String password, String email, String role) {
        super(userID, name, password, email, role);
        this.freetimes = new ArrayList<>();
    }

    /**
     * Sets the availability of the doctor.
     *
     * @param input the availability to set
     */
    private void setAvailability(Boolean input) {
        availability = input;
    }

    /**
     * Gets the availability of the doctor.
     *
     * @return the availability of the doctor
     */
    private boolean getAvailability() {
        return availability;
    }

    /**
     * Gets the list of free times of the doctor.
     *
     * @return the list of free times
     */
    public List<String> getFreetimes() {
        return freetimes;
    }

    /**
     * Adds a new free time to the list of free times.
     *
     * @param newFreeTime the new free time to add
     */
    public void setFreetimes(String newFreeTime) {
        freetimes.add(newFreeTime);
    }
}