package use_case.create_food;

/**
 * Output boundary for the Create Food Event.
 */
public interface CreateFoodOutputBoundary {

    /**
     * Prepares the view when a food event is created successfully.
     *
     * @param outputData the result of creating a food event
     */
    void prepareSuccessView(CreateFoodOutputData outputData);

    /**
     * Prepares the view when a food event cannot be created.
     *
     * @param errorMessage the reason why the event could not be created
     */
    void prepareFailView(String errorMessage);

}

