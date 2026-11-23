package use_case.report_food_event;

import entity.FoodEvent;
import entity.Report;

/**
 * Data access interface for reporting a food event.
 */
public interface ReportFoodEventDataAccessInterface {

    FoodEvent getFoodEventById(String eventId);

    void saveReport(Report report);
}
