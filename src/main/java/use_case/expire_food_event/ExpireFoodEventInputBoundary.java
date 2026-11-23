package use_case.expire_food_event;

/**
 * Input boundary for the expire food event use case.
 */
public interface ExpireFoodEventInputBoundary {

    void execute(ExpireFoodEventInputData inputData);
}
