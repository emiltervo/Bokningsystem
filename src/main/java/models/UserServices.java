package models;

import java.util.ArrayList;
import java.util.List;

import static models.UserRepository.getUserList;

public class UserServices {
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

    public User getPatientDetails(String selectedItem) {
        ArrayList<User> users = UserRepository.getUserList();
        String[] parts = selectedItem.split(" ");
        String selectedID = parts[parts.length - 1];
        for(User who : users) {
            if (String.valueOf(who.getUserID()).equals(selectedID)) {
               return who;
            }
        }
        return null;
    }
}
