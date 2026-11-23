package use_case.report_food_event;

/**
 * Output boundary for reporting a food event.
 */
public interface ReportFoodEventOutputBoundary {

    void prepareSuccessView(ReportFoodEventOutputData outputData);

    void prepareFailView(String errorMessage);
}

