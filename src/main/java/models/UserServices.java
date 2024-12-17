package models;

import java.util.ArrayList;
import static models.UserRepository.getUserList;

/**
 * The UserServices class contains methods for populating a box with usernames and extracting usernames and IDs.
 */
public class UserServices {

    /**
     * Populates a list with usernames and IDs.
     *
     * @return an ArrayList of strings containing usernames and their corresponding IDs
     */
    public ArrayList<String> populateBox() {
        ArrayList<Patient> users = UserRepository.getPatientList();
        ArrayList<String> fullNamePlusID = new ArrayList<>();

        for (User user : users) {
            String fullName = user.getName();
            long userID = user.getUserID();
            fullNamePlusID.add(fullName + " " + userID);
        }

        return fullNamePlusID;
    }

    /**
     * Extracts usernames and IDs.
     *
     * @return an ArrayList of strings containing usernames and their corresponding IDs
     */
    public ArrayList<String> extractBoth() {
        ArrayList<User> users = getUserList();
        ArrayList<String> nameAndId = new ArrayList<>();

        for (User user : users) {
            String fullName = user.getName();
            long userID = user.getUserID();
            String userIDToString = Long.toString(userID);
            nameAndId.add(fullName + " " + userIDToString);
        }

        return nameAndId;
    }

    /**
     * Gets the details of a specific patient.
     *
     * @param userID the ID of the patient
     * @return the User object containing the patient's details, or null if not found
     */
    public User getPatientDetails(long userID) {
        ArrayList<Patient> users = UserRepository.getPatientList();

        for (User who : users) {
            if (who.getUserID() == userID) {
                return who;
            }
        }
        return null;
    }
}