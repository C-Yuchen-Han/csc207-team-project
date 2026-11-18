
package entity;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Represents a free food event on the UTSG campus.
 */
public class FoodEvent {
  private final String eventId;
  private final String title;
  private final String description;
  private final Location location;
  private final Set<FoodType> foodTypes;
  private final Set<Subject> relatedSubjects;
  private final boolean registrationRequired;
  private final LocalDateTime createdAt;
  private final LocalDateTime expiresAt;

  /**
   * Constructs a FoodEvent object.
   *
   * @param eventId the unique identifier of the event
   * @param title the title of the event
   * @param description the description of the event
   * @param location the location of the event
   * @param foodTypes the types of food available
   * @param relatedSubjects the academic subjects related to the event
   * @param registrationRequired whether registration is required
   * @param createdAt the time when the event was created
   * @param expiresAt the time when the event expires
   */
  public FoodEvent(String eventId, String title, String description,
                   Location location, Set<FoodType> foodTypes,
                   Set<Subject> relatedSubjects, boolean registrationRequired,
                   LocalDateTime createdAt, LocalDateTime expiresAt) {
    this.eventId = eventId;
    this.title = title;
    this.description = description;
    this.location = location;
    this.foodTypes = Set.copyOf(foodTypes);
    this.relatedSubjects = Set.copyOf(relatedSubjects);
    this.registrationRequired = registrationRequired;
    this.createdAt = createdAt;
    this.expiresAt = expiresAt;
  }

  /**
   * Returns the unique identifier of the event.
   *
   * @return the event identifier
   */
  public String getEventId() {
    return eventId;
  }

  /**
   * Returns the title of the event.
   *
   * @return the event title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Returns the description of the event.
   *
   * @return the event description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Returns the location of the event.
   *
   * @return the event location
   */
  public Location getLocation() {
    return location;
  }

  /**
   * Returns the types of food available at the event.
   *
   * @return an unmodifiable set of food types
   */
  public Set<FoodType> getFoodTypes() {
    return foodTypes;
  }

  /**
   * Returns the academic subjects related to the event.
   *
   * @return an unmodifiable set of related subjects
   */
  public Set<Subject> getRelatedSubjects() {
    return relatedSubjects;
  }

  /**
   * Returns whether registration is required.
   *
   * @return true if registration is required, false otherwise
   */
  public boolean isRegistrationRequired() {
    return registrationRequired;
  }

  /**
   * Returns the time when the event was created.
   *
   * @return the creation time
   */
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  /**
   * Returns the time when the event expires.
   *
   * @return the expiration time
   */
  public LocalDateTime getExpiresAt() {
    return expiresAt;
  }
}
