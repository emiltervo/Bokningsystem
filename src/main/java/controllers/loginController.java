package controllers;

import models.User;
import views.LoginView;

public class loginController {
    private LoginView loginView;

    public loginController(LoginView loginView) {
        this.loginView = loginView;
        this.loginView.setController(this);
    }



}
