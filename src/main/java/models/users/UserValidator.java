package models.users;

import java.util.ArrayList;

/**
 * The UserValidator class validates user input and interacts with the UserServices to manage user data.
 */
public class UserValidator {

    private final UserServices userServices;

    /**
     * Constructs a UserValidator with the specified UserServices.
     *
     * @param userServices the UserServices to be used by this validator
     */
    public UserValidator(UserServices userServices) {
        this.userServices = userServices;
    }

    /**
     * Validates the user input fields when creating a new user.
     *
     * @param name the first name of the user
     * @param surname the surname of the user
     * @param personnummer the personal number of the user
     * @param password the password of the user
     * @param email the email address of the user
     * @param role the role of the user
     * @return a validation message indicating success or listing errors
     */
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
     * Ships a new user to the database.
     *
     * @param personnummer the personal number of the user
     * @param name the first name of the user
     * @param surname the surname of the user
     * @param password the password of the user
     * @param email the email address of the user
     * @param role the role of the user
     */
    public void shipNewUser(Long personnummer, String name, String surname, String password, String email, String role) {
        String fullName = name + " " + surname;
        UserRepository.addUser(personnummer, fullName, password, email, role);
        System.out.println("Ready to add to database:" + " " + fullName);
    }

    /**
     * Checks the input from the search box and redirects the responsibility based on the input type.
     *
     * @param input the input from the search box
     * @return a list of search matches
     */
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

    /**
     * Searches through names and numbers in the database.
     *
     * @param input the input to search for
     * @return a list of matches
     */
    private ArrayList<String> letterCheck(String input) {
        ArrayList<String> matches = new ArrayList<>();
        ArrayList<String> nameAndId = userServices.extractBoth();

        for (String fullNameAndId : nameAndId) {
            if (fullNameAndId.toLowerCase().contains(input.toLowerCase())) {
                matches.add(fullNameAndId);
            }
        }
        return matches;
    }

    /**
     * Searches through numbers only in the database (personnummer) for quick searches.
     *
     * @param input the input to search for
     * @return a list of matches
     */
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