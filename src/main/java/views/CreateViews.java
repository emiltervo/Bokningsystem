package views;

import controllers.CalendarController;
import controllers.HomeViewController;

public class CreateViews {
    private static CreateViews instance;
    private BookedTimesViewPatient bookedTimesViewPatient;
    private HomeView homeView;
    private HomeViewPatient homeViewPatient;
    private LoginView loginView;
    private PatientView patientView;
    private CalendarView calendarView;
    private RecipeView recipeView;
    private RecipeViewPatient recipeViewPatient;
    private CalendarPopupView calendarPopupView;

    private CreateViews() {
        // Private constructor to prevent instantiation
    }

    public static synchronized CreateViews getInstance() {
        if (instance == null) {
            instance = new CreateViews();
        }
        return instance;
    }

    public HomeView getHomeView() {
        if (homeView == null) {
            homeView = new HomeView();
            HomeViewController controller = new HomeViewController(homeView);
            homeView.setController(controller);
        }
        return homeView;
    }

    public BookedTimesViewPatient getBookedTimesViewPatient() {
        if (bookedTimesViewPatient == null) {
            bookedTimesViewPatient = new BookedTimesViewPatient();
        }
        return bookedTimesViewPatient;
    }

    public RecipeViewPatient getRecipeViewPatient() {
        if (recipeViewPatient == null) {
            recipeViewPatient = new RecipeViewPatient();
        }
        return recipeViewPatient;
    }


    public RecipeView getRecipeView() {
        if (recipeView == null) {
            recipeView = new RecipeView();
        }
        return recipeView;
    }


    public HomeViewPatient getHomeViewPatient() {
        if (homeViewPatient == null) {
            homeViewPatient = new HomeViewPatient();
        }
        return homeViewPatient;
    }

    public LoginView getLoginView() {
        if (loginView == null) {
            loginView = new LoginView();
        }
        return loginView;
    }


    public CalendarView getCalendarView() {
        if (calendarView == null) {
            calendarView = new CalendarView();
            CalendarController controller = new CalendarController(calendarView);
        }
        return calendarView;
    }


    public PatientView getSearchLogicView() {
        if (patientView == null) {
            patientView = new PatientView();
        }
        return patientView;
    }
}
