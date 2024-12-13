package controllers;

import models.User;
import models.UserRepository;
import views.IHandleLogin;
import views.LoginView;

/** Controller for the LoginView */

public class LoginController implements IHandleLogin {
    private final LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.loginView.setLoginHandler(this);
    }
    @Override
    public boolean handleLogin(long userID, String password) {
        User user = UserRepository.getUserByID(userID);
        if (user != null && user.login(userID, password)) {
            if (user.getRole().equals("doctor") || user.getRole().equals("secretary")) {
                loginView.showSuccessAdmin();
            } else {loginView.showSuccessPatient();
            }
            return true;
        } else {
            loginView.showError("Invalid userID or password");
            return false;
        }
    }
}

