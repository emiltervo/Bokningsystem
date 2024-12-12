package controllers;

import models.UserValidator;
import views.PatientView;

public class PatientViewController {
    private PatientView view;
    private UserValidator userValidator;
    public PatientViewController(PatientView view) {
        this.view = view;
        this.userValidator = new UserValidator();
    }
    public String inputFromUser(String name, String surname, String personnummer, String password, String email, String role) {
        String validationMessage = userValidator.isValid(name, surname, personnummer, password, email, role);

        if (validationMessage.equals("Valid")) {

            userValidator.shipNewUser(name, surname, personnummer, password, email, role);
        } else {

            view.showErrorMessage(validationMessage);
        }

        return validationMessage;
    }

}