
package use_case.browse_food;

import entity.FoodType;
import entity.Subject;
import java.util.Set;

/**
 * Input data containing the criteria for browsing food events.
 */
public class BrowseFoodInputData {
  private final String keyword;
  private final String buildingName;
  private final Set<FoodType> foodTypes;
  private final Set<Subject> relatedSubjects;
  private final boolean noRegistrationOnly;
  private final Double maxDistanceMeters;
  private final Double userLatitude;
  private final Double userLongitude;

  /**
   * Constructs the input data for browsing food events.
   *
   * @param keyword the search keyword
   * @param buildingName the building name filter
   * @param foodTypes the selected food types
   * @param relatedSubjects the selected academic subjects
   * @param noRegistrationOnly whether to show only events without registration
   * @param maxDistanceMeters the maximum distance in meters, or null if unrestricted
   * @param userLatitude the latitude of the reference location
   * @param userLongitude the longitude of the reference location
   */
  public BrowseFoodInputData(
      String keyword,
      String buildingName,
      Set<FoodType> foodTypes,
      Set<Subject> relatedSubjects,
      boolean noRegistrationOnly,
      Double maxDistanceMeters,
      Double userLatitude,
      Double userLongitude) {
    this.keyword = keyword;
    this.buildingName = buildingName;
    this.foodTypes = Set.copyOf(foodTypes);
    this.relatedSubjects = Set.copyOf(relatedSubjects);
    this.noRegistrationOnly = noRegistrationOnly;
    this.maxDistanceMeters = maxDistanceMeters;
    this.userLatitude = userLatitude;
    this.userLongitude = userLongitude;
  }

  /**
   * Returns the search keyword.
   *
   * @return the search keyword
   */
  public String getKeyword() {
    return keyword;
  }

  /**
   * Returns the building name filter.
   *
   * @return the building name
   */
  public String getBuildingName() {
    return buildingName;
  }

  /**
   * Returns the selected food types.
   *
   * @return an unmodifiable set of food types
   */
  public Set<FoodType> getFoodTypes() {
    return foodTypes;
  }

  /**
   * Returns the selected academic subjects.
   *
   * @return an unmodifiable set of related subjects
   */
  public Set<Subject> getRelatedSubjects() {
    return relatedSubjects;
  }

  /**
   * Returns whether only events without registration should be shown.
   *
   * @return true if only events without registration should be shown
   */
  public boolean isNoRegistrationOnly() {
    return noRegistrationOnly;
  }

  /**
   * Returns the maximum distance in meters.
   *
   * @return the maximum distance, or null if unrestricted
   */
  public Double getMaxDistanceMeters() {
    return maxDistanceMeters;
  }

  /**
   * Returns the latitude of the reference location.
   *
   * @return the reference latitude, or null if not provided
   */
  public Double getUserLatitude() {
    return userLatitude;
  }

  /**
   * Returns the longitude of the reference location.
   *
   * @return the reference longitude, or null if not provided
   */
  public Double getUserLongitude() {
    return userLongitude;
  }
}
