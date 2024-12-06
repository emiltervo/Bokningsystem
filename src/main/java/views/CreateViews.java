package views;

public class CreateViews {
    private static CreateViews instance;

    public static CreateViews getInstance() {
        if (instance == null) {
            instance = new CreateViews();
        }
        return instance;
    }

    private LoginView loginView;
    private HomeView homeView;
    private HomeViewPatient homeViewPatient;
    private PatientView patientView;
    private CalendarView calendarView;
    private CalendarPopupView calendarPopupView;

    public LoginView getLoginView() {
        if (loginView == null) {
            loginView = new LoginView();
        }
        return loginView;
    }



    public HomeView getHomeView() {
        if (homeView == null) {
            homeView = new HomeView();
        }
        return homeView;
    }


    public HomeViewPatient getHomeViewPatient() {
        if (homeViewPatient == null) {
            homeViewPatient = new HomeViewPatient();
        }
        return homeViewPatient;
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
        }
        return calendarView;
    }

    /*public CalendarPopupView getCalendarPopupView() {
        if (calendarPopupView == null) {
            calendarPopupView = new CalendarPopupView();
        }
        return calendarPopupView;
    } */


}
