package use_case.create_food;

/**
 * Input boundary for the Create Food Event.
 */
public interface CreateFoodInputBoundary {

    /**
     * Handles the request to create a new food event.
     *
     * @param inputData the information needed to create a food event
     */
    void execute(CreateFoodInputData inputData);

}