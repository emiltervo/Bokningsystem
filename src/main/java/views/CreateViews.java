package views;

import controllers.CalendarController;
import controllers.HomeViewController;
import controllers.HomeViewPatientController;

public class CreateViews {
    private static CreateViews instance;
    private HomeView homeView;
    private HomeViewPatient homeViewPatient;
    private LoginView loginView;
    private PatientView patientView;
    private CalendarView calendarView;
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


    public HomeViewPatient getHomeViewPatient() {
        if (homeViewPatient == null) {
            homeViewPatient = new HomeViewPatient();
            HomeViewPatientController controller = new HomeViewPatientController(homeViewPatient);
            homeViewPatient.setController(controller);
        }
        return homeViewPatient;
    }

    public LoginView getLoginView() {
        if (loginView == null) {
            loginView = new LoginView();
        }
        return loginView;
    }


    public PatientView getPatientView() {
        if (patientView == null) {
            patientView = new PatientView();
        }
        return patientView;
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
