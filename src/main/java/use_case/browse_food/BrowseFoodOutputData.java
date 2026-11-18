
package use_case.browse_food;

import entity.FoodEvent;
import java.util.List;

/**
 * Output data containing food events that match the browse criteria.
 */
public class BrowseFoodOutputData {
  private final List<FoodEvent> foodEvents;

  /**
   * Constructs the output data for browsing food events.
   *
   * @param foodEvents the food events matching the browse criteria
   */
  public BrowseFoodOutputData(List<FoodEvent> foodEvents) {
    this.foodEvents = List.copyOf(foodEvents);
  }

  /**
   * Returns the food events matching the browse criteria.
   *
   * @return an unmodifiable list of matching food events
   */
  public List<FoodEvent> getFoodEvents() {
    return foodEvents;
  }
}
