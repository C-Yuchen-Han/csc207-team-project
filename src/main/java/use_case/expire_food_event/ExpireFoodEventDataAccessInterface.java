package use_case.expire_food_event;

import entity.FoodEvent;
import java.util.List;

/**
 * Data access interface for expiring food events.
 */
public interface ExpireFoodEventDataAccessInterface {

    List<FoodEvent> getAllActiveEvents();

    void removeFromActiveListings(String eventId);
}
