package models;

import java.awt.*;
import java.util.ArrayList;

public class UserValidator {
    public void shipNewUser(String name, String surname, String personnummer, String password, String email, String role) {
        ArrayList<String> userToShip = new ArrayList<>();

        userToShip.add(personnummer);
        userToShip.add(name + " " + surname);
        userToShip.add(password);
        userToShip.add(email);
        userToShip.add(role);

        System.out.println("Ready to add to database:" + " " + userToShip);
    }
}
