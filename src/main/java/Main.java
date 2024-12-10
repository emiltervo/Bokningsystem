import controllers.CalendarController;
import controllers.loginController;
import models.*;
import views.CalendarView;
import views.CreateViews;
import views.LoginView;


public class Main {
    public static void main(String[] args) {

        UserRepository.getAllUsers();
        CreateViews createviews = CreateViews.getInstance();
        LoginView loginView = createviews.getLoginView();
        loginController loginController = new loginController(loginView);
        loginView.createUI();


    }
}
