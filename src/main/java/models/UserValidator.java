package models;

import java.awt.*;
import java.util.ArrayList;
import models.*;

public class UserValidator {

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

        if (!email.contains("@") || (!email.contains(".")) ) {
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

    public void shipNewUser(Long personnummer, String name, String surname, String password, String email, String role) {



        String fullName = name + " " + surname;

        UserRepository.addUser(personnummer, fullName, password, email, role);
        System.out.println("Ready to add to database:" + " " + name);
    }
}
