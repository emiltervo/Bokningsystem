package controllers;

import models.UserValidator;
import models.UserServices;
import views.PatientView;

import java.util.ArrayList;

//** Controller for the PatientView */

public class PatientViewController {
    private PatientView view;
    private UserValidator userValidator;
    private UserServices userServices;

    //Constructor
    public PatientViewController(PatientView view) {
        this.view = view;
        this.userValidator = new UserValidator();
        this.userServices = new UserServices();
    }

    // Method send input from user to UserValidator in models
    public String inputFromUser(String name, String surname, String personnummer, String password, String email, String role) {
        String validationMessage = userValidator.isValid(name, surname, personnummer, password, email, role);

        if (validationMessage.equals("Valid")) {

            userValidator.shipNewUser(Long.valueOf(name), surname, personnummer, password, email, role);
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

}