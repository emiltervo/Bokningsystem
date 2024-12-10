package controllers;

import views.CalendarView;
import views.CreateViews;
import views.HomeView;

public class HomeViewController {
    private final HomeView homeView;

    public HomeViewController(HomeView homeView) {
        this.homeView = homeView;
    }

    public void navigateToCalendarView() {
        //homeView.setVisible(false); // Hide the current view
        //CreateViews.getInstance().getCalendarView().setVisible(true); // Open the CalendarView
    }
}
