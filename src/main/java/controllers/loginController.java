package controllers;

import models.User;
import models.UserRepository;
import views.LoginView;

public class loginController {
    private final LoginView loginView;

    public loginController(LoginView loginView) {
        this.loginView = loginView;
        this.loginView.setController(this);
    }

    public boolean handleLogin(long userID, String password) {
        User user = UserRepository.getUserByID(userID);
        if (user != null && user.login(userID, password)) {
            if (user.getRole().equals("doctor") || user.getRole().equals("secretary")) {
                loginView.showSuccessAdmin();
                return true;
            } else {loginView.showSuccessPatient(); return true;}
        } else {
            loginView.showError("Invalid userID or password");
            return false;
        }
    }

}
