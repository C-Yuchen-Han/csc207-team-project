package entity;

import java.util.Set;

public class NotificationPreference {
    private final String userId;
    private final Set<FoodType> foodTypes;
    private final Set<Subject> relatedSubjects;
    private final Set<String> buildingNames;
    private final boolean noRegistrationOnly;

    public NotificationPreference(
            String userId,
            Set<FoodType> foodTypes,
            Set<Subject> relatedSubjects,
            Set<String> buildingNames,
            boolean noRegistrationOnly) {

        this.userId = userId;
        this.foodTypes = Set.copyOf(foodTypes);
        this.relatedSubjects = Set.copyOf(relatedSubjects);
        this.buildingNames = Set.copyOf(buildingNames);
        this.noRegistrationOnly = noRegistrationOnly;
    }

    public String getUserId() {
        return userId;
    }

    public Set<FoodType> getFoodTypes() {
        return foodTypes;
    }

    public Set<Subject> getRelatedSubjects() {
        return relatedSubjects;
    }

    public Set<String> getBuildingNames() {
        return buildingNames;
    }

    public boolean isNoRegistrationOnly() {
        return noRegistrationOnly;
    }
}
