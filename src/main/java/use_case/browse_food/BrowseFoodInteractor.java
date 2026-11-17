
package use_case.browse_food;

import entity.FoodEvent;
import entity.FoodType;
import entity.Subject;

import java.util.*;

/**
 * Interactor responsible for browsing and filtering food events.
 * Applies the selected filters and sends matching events to the presenter.
 */
public class BrowseFoodInteractor implements BrowseFoodInputBoundary {

  private final FoodEventRepository repository;
  private final BrowseFoodOutputBoundary presenter;
  private final DistanceCalculator distanceCalculator;

  /**
   * Constructs a BrowseFoodInteractor with its required dependencies.
   *
   * @param repository the repository used to retrieve food events
   * @param presenter the output boundary for browse results
   * @param distanceCalculator the service used to calculate walking distances
   */
  public BrowseFoodInteractor(
      FoodEventRepository repository,
      BrowseFoodOutputBoundary presenter,
      DistanceCalculator distanceCalculator) {
    this.repository = repository;
    this.presenter = presenter;
    this.distanceCalculator = distanceCalculator;
  }

  /**
   * Retrieves food events and applies all selected filters.
   * An event is included only if it satisfies every active filter.
   *
   * @param inputData the user's browsing and filtering preferences
   */
  @Override
  public void execute(BrowseFoodInputData inputData) {
    List<FoodEvent> events = repository.getAllEvents();
    List<FoodEvent> filteredEvents = new ArrayList<>();

    // Apply inexpensive filters first to avoid unnecessary API calls.
    for (FoodEvent event : events) {
      if (matchesKeyword(event, inputData.getKeyword())
          && matchesBuilding(event, inputData.getBuildingName())
          && matchesRegistration(event, inputData.isNoRegistrationOnly())
          && matchesFoodType(event, inputData.getFoodTypes())
          && matchesRelatedSubjects(event, inputData.getRelatedSubjects())
          && matchesDistance(
          event,
          inputData.getMaxDistanceMeters(),
          inputData.getUserLatitude(),
          inputData.getUserLongitude())) {
        filteredEvents.add(event);
      }
    }

    // Pass the filtered results to the presentation layer.
    BrowseFoodOutputData outputData = new BrowseFoodOutputData(filteredEvents);
    presenter.prepareSuccessView(outputData);
  }

  /**
   * Checks whether the event title contains the search keyword.
   * Matching is case-insensitive.
   */
  private boolean matchesKeyword(FoodEvent event, String keyword) {
    return keyword.isEmpty()
        || event.getTitle().toLowerCase().contains(keyword.toLowerCase().trim())
        || event.getDescription().toLowerCase().contains(keyword.toLowerCase().trim());
  }

  /**
   * Checks whether the event is located in the selected building.
   * An empty building name disables this filter.
   */
  private boolean matchesBuilding(FoodEvent event, String buildingName) {
    return buildingName.isEmpty()
        || event.getLocation().getBuilding().getBuildingName()
        .equalsIgnoreCase(buildingName);
  }

  /**
   * Checks whether the event offers at least one selected food type.
   * An empty selection disables this filter.
   */
  private boolean matchesFoodType(FoodEvent event, Set<FoodType> selectedTypes) {
    return selectedTypes.isEmpty()
        || !Collections.disjoint(event.getFoodTypes(), selectedTypes);
  }

  /**
   * Checks whether the event matches at least one selected subject.
   * An empty selection disables this filter.
   */
  private boolean matchesRelatedSubjects(FoodEvent event, Set<Subject> selectedSubjects) {
    return selectedSubjects.isEmpty()
        || !Collections.disjoint(event.getRelatedSubjects(), selectedSubjects);
  }

  /**
   * Filters out events requiring registration when requested.
   */
  private boolean matchesRegistration(FoodEvent event, boolean noRegistrationOnly) {
    return !noRegistrationOnly
        || !event.isRegistrationRequired();
  }

  /**
   * Checks whether the event is within the selected walking distance.
   * No API call is made when the distance filter is disabled.
   */
  private boolean matchesDistance(
      FoodEvent event,
      Double maxDistanceMeters,
      Double userLatitude,
      Double userLongitude) {

    if (maxDistanceMeters == null) {
      return true;
    }

    // User coordinates are required when distance filtering is enabled.
    if (userLatitude == null || userLongitude == null) {
      throw new IllegalArgumentException(
          "User coordinates are required for distance filtering");
    }

    // Calculate walking distance using the injected distance service.
    double eventDistance = distanceCalculator.calculateDistance(
        userLatitude,
        userLongitude,
        event.getLocation().getBuilding().getLatitude(),
        event.getLocation().getBuilding().getLongitude()
    );

    return eventDistance <= maxDistanceMeters;
  }
}
