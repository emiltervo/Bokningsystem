package controllers;

import models.User;
import models.UserValidator;
import models.UserServices;
import views.PatientView;

import java.util.ArrayList;
import java.util.Arrays;

//** Controller for the PatientView */

public class PatientViewController {
    private PatientView view;
    private UserValidator userValidator;
    private UserServices userServices;

    //Constructor
    public PatientViewController(PatientView view) {
        this.view = view;
        this.userServices = new UserServices();
        this.userValidator = new UserValidator(userServices);

    }

    // Method send input from user to UserValidator in models
    public String inputFromUser(String name, String surname, String personnummer, String password, String email, String role) {
        String validationMessage = userValidator.isValid(name, surname, personnummer, password, email, role);

        if (validationMessage.equals("Valid")) {

            userValidator.shipNewUser(Long.valueOf(personnummer), name, surname, password, email, role);
        } else {

            view.showErrorMessage(validationMessage);
        }

        return validationMessage;
    }

    // Method to populate the combobox with patients
    public void populateComboBox() {
        ArrayList<String> patients = userServices.populateBox();
        view.populateComboBox(patients);
    }

    public void populatePatientDetails(String selectedItem) {

        String[] parts = selectedItem.split(" ");
        String selectedID = parts[parts.length-1];
        long userID = Long.parseLong(selectedID);

        User user = userServices.getPatientDetails(userID);

        if (user!=null) {
            view.updatePatientDetails(user);
        }
    }

    public void handleInput(String input) {
        ArrayList<String> results = userValidator.checkInput(input);

        // Pings view to update with results
        view.updateComboBox(results);
    }
}
