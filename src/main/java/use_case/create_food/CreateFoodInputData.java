
package use_case.create_food;

import entity.FoodType;
import entity.Location;
import entity.Subject;

import java.time.LocalDateTime;
import java.util.Set;

public class CreateFoodInputData {

    private final String title;
    private final String description;
    private final Location location;
    private final Set<FoodType> foodTypes;
    private final Set<Subject> relatedSubjects;
    private final boolean registrationRequired;
    private final LocalDateTime expiresAt;

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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    public Set<FoodType> getFoodTypes() {
        return foodTypes;
    }

    public Set<Subject> getRelatedSubjects() {
        return relatedSubjects;
    }

    public boolean isRegistrationRequired() {
        return registrationRequired;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
}
