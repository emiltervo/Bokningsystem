package models;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import models.*;
import views.PatientView;
import models.*;

/** UserValidator class that validates user input */
public class UserValidator {

    private final UserServices userServices;

    public UserValidator(UserServices userServices) {
        this.userServices = userServices;
    }

    public String isValid(String name, String surname, String personnummer, String password, String email, String role) {

        StringBuilder allErrors = new StringBuilder();


        if (name.isEmpty() || !(name.matches("[a-zA-Z]+"))) {
            allErrors.append("First name, ");
        }

        if (surname.isEmpty() || !(name.matches("[a-zA-Z]+"))) {
            allErrors.append("Surname, ");
        }

        if ((personnummer.length() != 12) || !(personnummer.matches(("\\d+")))) {
            allErrors.append("Personnummer, ");
        }

        if (!email.contains("@") || (!email.contains("."))) {
            allErrors.append("Email, ");
        }

        if (password.isEmpty()) {
            allErrors.append("Password, ");
        }

        if (role.isEmpty()) {
            allErrors.append("Role, ");
        }

        if (!allErrors.isEmpty()) {
            allErrors.setLength(allErrors.length() - 2);
            return "Try again! Error(s): " + allErrors;
        }

        return "Valid";
    }

    /**
     * Method to ship new user to database
     */
    public void shipNewUser(Long personnummer, String name, String surname, String password, String email, String role) {


        String fullName = name + " " + surname;

        UserRepository.addUser(personnummer, fullName, password, email, role);
        System.out.println("Ready to add to database:" + " " + fullName);
    }

    public ArrayList<String> checkInput(String input) {
        ArrayList<String> searchMatches = new ArrayList<>();

        if (input.matches(".*[a-zA-Z].*") && (input.matches(".*\\d.*"))) {
            searchMatches = letterCheck(input);

        } else if ((input.matches(".*[a-zA-Z]"))) {
            searchMatches = letterCheck(input);

        } else if (input.matches(".*\\d.*")) {
            searchMatches = numberCheck(input);
        } else if (input.isEmpty()) {
            searchMatches = userServices.populateBox();
        }

        return searchMatches;

    }

    private ArrayList<String> letterCheck(String input) {
        ArrayList<String> matches = new ArrayList<>();
        ArrayList<String> nameAndId = userServices.extractBoth();

        for (int abc = 0; abc < nameAndId.size(); abc++) {
            String fullNameAndId = nameAndId.get(abc);
            if (fullNameAndId.toLowerCase().contains(input.toLowerCase())) {
                matches.add(fullNameAndId);

            }
        }
        return matches;
    }

    private ArrayList<String> numberCheck(String input) {
        ArrayList<String> matches = new ArrayList<>();
        ArrayList<String> nameAndId = userServices.extractBoth();

        for (String fullNameAndId : nameAndId) {

            String[] iWantTheID = fullNameAndId.split(" ");
            String userID = iWantTheID[iWantTheID.length - 1];

            if (userID.contains(input)) {
                matches.add(fullNameAndId);
            }
        }
        return matches;
    }

}
