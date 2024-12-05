import controllers.CalendarController;
import models.*;
import views.CalendarView;


public class Main {
    public static void main(String[] args) {
        CalendarView calendarView = new CalendarView();
        CalendarController calendarController = new CalendarController(calendarView);
    }
}
