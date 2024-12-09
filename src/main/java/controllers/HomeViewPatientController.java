package controllers;

import views.CalendarView;
import views.HomeViewPatient;

public class HomeViewPatientController {
    private HomeViewPatient homeViewPatient;

    public HomeViewPatientController(HomeViewPatient homeViewPatient) {
        this.homeViewPatient = homeViewPatient;
    }

    public void navigateToCalendarView() {
        homeViewPatient.setVisible(false); // Hide the current view
        new CalendarView(); // Open the CalendarView
    }
}
