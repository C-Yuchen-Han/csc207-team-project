
package interface_adapter.browse_food;

import entity.FoodType;
import entity.Subject;
import java.util.Set;
import use_case.browse_food.BrowseFoodInputBoundary;
import use_case.browse_food.BrowseFoodInputData;

/**
 * Controller for browsing and filtering food events.
 */
public class BrowseFoodController {

  private final BrowseFoodInputBoundary interactor;

  /**
   * Constructs a BrowseFoodController.
   *
   * @param interactor the input boundary for browsing food events
   */
  public BrowseFoodController(BrowseFoodInputBoundary interactor) {
    this.interactor = interactor;
  }

  /**
   * Executes the browse food use case with the given filters.
   *
   * @param keyword the search keyword
   * @param buildingName the building name filter
   * @param foodTypes the selected food types
   * @param relatedSubjects the selected subjects
   * @param registrationRequired the registration filter
   * @param distanceWithin the maximum distance
   * @param userLatitude the user's latitude
   * @param userLongitude the user's longitude
   */
  public void execute(String keyword,
                      String buildingName,
                      Set<FoodType> foodTypes,
                      Set<Subject> relatedSubjects,
                      Boolean registrationRequired,
                      Double distanceWithin,
                      Double userLatitude,
                      Double userLongitude) {
    final BrowseFoodInputData inputData = new BrowseFoodInputData(
        keyword, buildingName, foodTypes, relatedSubjects,
        registrationRequired, distanceWithin, userLatitude, userLongitude
    );

    interactor.execute(inputData);
  }
}
