package controllers;

import models.UserValidator;
import models.UserServices;
import views.PatientView;

import java.util.ArrayList;

public class PatientViewController {
    private PatientView view;
    private UserValidator userValidator;
    private UserServices userServices;

    public PatientViewController(PatientView view) {
        this.view = view;
        this.userValidator = new UserValidator();
        this.userServices = new UserServices();
    }
    public String inputFromUser(String name, String surname, String personnummer, String password, String email, String role) {
        String validationMessage = userValidator.isValid(name, surname, personnummer, password, email, role);

        if (validationMessage.equals("Valid")) {

            userValidator.shipNewUser(Long.valueOf(name), surname, personnummer, password, email, role);
        } else {

            view.showErrorMessage(validationMessage);
        }

        return validationMessage;
    }

    public void populateComboBox() {
        ArrayList<String> patients = userServices.populateBox();
        view.populateComboBox(patients);
    }

}