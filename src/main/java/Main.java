
import models.recipes.RecipeRepository;
import models.users.UserRepository;
import views.CreateViews;
import views.LoginView;
import controllers.LoginController;


public class Main {
    public static void main(String[] args) {

        UserRepository.getAllUsers();
        RecipeRepository.getAllRecipes();
        CreateViews createviews = CreateViews.getInstance();
        LoginView loginView = createviews.getLoginView();
        LoginController loginController = new LoginController(loginView);
        loginView.createUI();

    }
}
