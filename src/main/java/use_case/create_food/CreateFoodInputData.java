package use_case.create_food;

import entity.FoodType;
import entity.Location;
import entity.Subject;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Stores the information entered by the student when creating a
 * new food event.
 */
public class CreateFoodInputData {

    private final String title;
    private final String description;
    private final Location location;
    private final Set<FoodType> foodTypes;
    private final Set<Subject> relatedSubjects;
    private final boolean registrationRequired;
    private final LocalDateTime expiresAt;

    /**
     * Constructs a CreateFoodInputData event.
     *
     * @param title the title of the event
     * @param description the description of the event
     * @param location the location of the event
     * @param foodTypes the types of food available
     * @param relatedSubjects the academic subjects related to the event
     * @param registrationRequired whether registration is required
     * @param expiresAt the time when the event expires
     */
    public CreateFoodInputData(
            String title,
            String description,
            Location location,
            Set<FoodType> foodTypes,
            Set<Subject> relatedSubjects,
            boolean registrationRequired,
            LocalDateTime expiresAt) {

        this.title = title;
        this.description = description;
        this.location = location;
        this.foodTypes = Set.copyOf(foodTypes);
        this.relatedSubjects = Set.copyOf(relatedSubjects);
        this.registrationRequired = registrationRequired;
        this.expiresAt = expiresAt;
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
     * @return event description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the location of the event.
     *
     * @return event location
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
     * Returns the time when the event expires.
     *
     * @return the expiration time
     */
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
}
