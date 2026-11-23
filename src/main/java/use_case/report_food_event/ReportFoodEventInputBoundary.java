package use_case.report_food_event;

/**
 * Input boundary for reporting a food event.
 */
public interface ReportFoodEventInputBoundary {

    void execute(ReportFoodEventInputData inputData);
}
