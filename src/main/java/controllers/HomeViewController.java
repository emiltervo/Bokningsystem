package controllers;

import views.CalendarView;
import views.HomeView;

public class HomeViewController {
    private HomeView homeView;

    public HomeViewController(HomeView homeView) {
        this.homeView = homeView;
    }

    public void navigateToCalendarView() {
        homeView.setVisible(false); // Hide the current view
        new CalendarView(); // Open the CalendarView
    }
}
