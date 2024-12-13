
import models.*;
import views.CreateViews;
import views.LoginView;
import controllers.LoginController;


public class Main {
    public static void main(String[] args) {

        UserRepository.getAllUsers();
        CreateViews createviews = CreateViews.getInstance();
        LoginView loginView = createviews.getLoginView();
        LoginController loginController = new LoginController(loginView);
        loginView.createUI();


    }
}
