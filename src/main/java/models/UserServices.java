package models;

import java.util.ArrayList;
import java.util.List;

import static models.UserRepository.getUserList;

/** UserServices class that contains methods for populating a box with user names and extracting user names and IDs */

public class UserServices {

    // Method for populating a box with user names and IDs
    public ArrayList<String> populateBox() {

        ArrayList<User> users = UserRepository.getUserList();
        ArrayList<String> fullNamePlusID = new ArrayList<>();

        for (User user : users) {

            String fullName = user.getName();
            long userID = user.getUserID();
            fullNamePlusID.add(fullName + " " + userID);

        }
        // Shipping >>> view.
        return fullNamePlusID;
    }

    // Method for extracting user names and IDs
    public ArrayList<String> extractBoth() {
        ArrayList<User> users = getUserList();
        ArrayList<String> nameAndId = new ArrayList<>();

        for (User user : users) {
            String fullName = user.getName();
            long userID = user.getUserID();
            String userIDToString = Long.toString(userID);

            nameAndId.add(fullName + " " + userIDToString);
        }
        // Shipping >>> view.
        return nameAndId;
    }
    // Method for getting patient details
    public User getPatientDetails(long userID) {
        ArrayList<User> users = UserRepository.getUserList();

        for (User who : users) {
            if (who.getUserID() == userID) {
                return who;
            }
        }
        return null;
    }
}
