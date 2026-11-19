package use_case.create_food;

import entity.FoodEvent;

/**
 * Data access interface for saving food events.
 */
public interface CreateFoodDataAccessInterface {

    /**
     * Saves a food event.
     *
     * @param event the food event to be saved
     */
    void save(FoodEvent event);

}
