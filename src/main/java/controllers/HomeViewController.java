package controllers;

/** Controller for the HomeView */

import views.CalendarView;
import views.HomeView;

public class HomeViewController {
    private final HomeView homeView;

    public HomeViewController(HomeView homeView) {
        this.homeView = homeView;
    }
}
