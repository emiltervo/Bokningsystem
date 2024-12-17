package models;

import java.util.List;
import java.util.ArrayList;

/** Doctor class that extends User */
// TODO: Consider if methods here that are not used should be removed

public class Doctor extends User {
    private boolean availability;
    private String specialty;
    private List<String> freetimes;

    public Doctor(long userID, String name, String password, String email, String role) {
        super(userID, name, password, email, role);
        this.freetimes = new ArrayList<>();
    }

    private void setAvailability(Boolean input) {
        availability = input;
    }

    private boolean getAvailability() {
        return availability;
    }

    public List<String> getFreetimes() {
        return freetimes;
    }

    public void setFreetimes(String newFreeTime) {
        freetimes.add(newFreeTime);
    }

}
