
package interface_adapter.browse_food;

/**
 * Represents the display information for a single food event.
 * Stores formatted data to be shown in the Browse Food view.
 */
public class FoodEventDisplayData {

  private final String eventId;
  private final String title;
  private final String description;
  private final String buildingName;
  private final String roomNumber;
  private final String foodTypes;
  private final String relatedSubjects;
  private final String registrationText;
  private final String createdAt;
  private final String expiresAt;

  /**
   * Constructs a FoodEventDisplayData object.
   *
   * @param eventId the unique identifier of the event
   * @param title the title of the event
   * @param description the description of the event
   * @param buildingName the name of the building
   * @param roomNumber the room number of the event
   * @param foodTypes the formatted food types
   * @param relatedSubjects the formatted related subjects
   * @param registrationText the registration requirement text
   * @param createdAt the formatted creation time
   * @param expiresAt the formatted expiration time
   */
  public FoodEventDisplayData(
      String eventId,
      String title,
      String description,
      String buildingName,
      String roomNumber,
      String foodTypes,
      String relatedSubjects,
      String registrationText,
      String createdAt,
      String expiresAt) {
    this.eventId = eventId;
    this.title = title;
    this.description = description;
    this.buildingName = buildingName;
    this.roomNumber = roomNumber;
    this.foodTypes = foodTypes;
    this.relatedSubjects = relatedSubjects;
    this.registrationText = registrationText;
    this.createdAt = createdAt;
    this.expiresAt = expiresAt;
  }

  /**
   * Returns the event identifier.
   *
   * @return the event identifier
   */
  public String getEventId() {
    return eventId;
  }

  /**
   * Returns the event title.
   *
   * @return the event title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Returns the event description.
   *
   * @return the event description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Returns the building name.
   *
   * @return the building name
   */
  public String getBuildingName() {
    return buildingName;
  }

  /**
   * Returns the room number.
   *
   * @return the room number
   */
  public String getRoomNumber() {
    return roomNumber;
  }

  /**
   * Returns the formatted food types.
   *
   * @return the food types
   */
  public String getFoodTypes() {
    return foodTypes;
  }

  /**
   * Returns the formatted related subjects.
   *
   * @return the related subjects
   */
  public String getRelatedSubjects() {
    return relatedSubjects;
  }

  /**
   * Returns the registration requirement text.
   *
   * @return the registration text
   */
  public String getRegistrationText() {
    return registrationText;
  }

  /**
   * Returns the formatted creation time.
   *
   * @return the creation time
   */
  public String getCreatedAt() {
    return createdAt;
  }

  /**
   * Returns the formatted expiration time.
   *
   * @return the expiration time
   */
  public String getExpiresAt() {
    return expiresAt;
  }
}
