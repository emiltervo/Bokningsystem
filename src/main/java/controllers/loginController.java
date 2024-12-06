package controllers;

import models.User;
import models.UserRepository;
import views.LoginView;

public class loginController {
    private LoginView loginView;

    public loginController(LoginView loginView) {
        this.loginView = loginView;
        this.loginView.setController(this);
    }

    public void handleLogin(int userID, String password) {
        User user = UserRepository.getUserByID(userID);
        if (user != null && user.login(userID, password)) {
            if (user.getRole().equals("doctor") || user.getRole().equals("secretary")) {
                loginView.showSuccessAdmin();
            } else {loginView.showSuccessPatient();}
        } else {
            loginView.showError("Invalid userID or password");
        }
    }

}
