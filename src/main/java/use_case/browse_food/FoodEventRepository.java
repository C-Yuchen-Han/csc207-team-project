package use_case.browse_food;

import entity.FoodEvent;
import java.util.List;

/**
 * Data access interface for retrieving food events.
 */
public interface FoodEventRepository {

  /**
   * Retrieves all stored food events.
   *
   * @return a list of food events
   */
  List<FoodEvent> getAllEvents();
}