
import models.*;
import views.CalendarView;
import views.CreateViews;
import views.LoginView;
import controllers.LoginController;
import views.PatientView;


public class Main {
    public static void main(String[] args) {

        UserRepository.getAllUsers();

        PatientView kung = new PatientView();
/*

        RecipeRepository.getAllRecipes();
        CreateViews createviews = CreateViews.getInstance();
        LoginView loginView = createviews.getLoginView();
        LoginController loginController = new LoginController(loginView);
        loginView.createUI();
*/




    }
}
