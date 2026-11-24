
package use_case.generate_notification;

import entity.FoodEvent;
import entity.Notification;
import entity.NotificationPreference;
import use_case.manage_notification.NotificationRepository;
import use_case.notification_preferences.NotificationPreferenceRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

public class GenerateNotificationInteractor {

    private final NotificationPreferenceRepository preferenceRepository;
    private final NotificationRepository notificationRepository;

    public GenerateNotificationInteractor(
            NotificationPreferenceRepository preferenceRepository,
            NotificationRepository notificationRepository) {

        this.preferenceRepository = preferenceRepository;
        this.notificationRepository = notificationRepository;
    }

    public void generateNotification(FoodEvent event) {

        // Check whether any preference has been saved.
        Optional<NotificationPreference> optionalPreference =
                preferenceRepository.findPreference();

        if (optionalPreference.isEmpty()) {
            return;
        }

        NotificationPreference preference = optionalPreference.get();

        // Check whether at least one preference has been selected.
        if (!hasAnyPreference(preference)) {
            return;
        }

        // Check whether the event matches the preferences.
        if (!matches(event, preference)) {
            return;
        }

        // Generate a notification.
        Notification notification = new Notification(
                UUID.randomUUID().toString(),
                event.getEventId(),
                event.getTitle(),
                LocalDateTime.now()
        );

        notificationRepository.save(notification);
    }

    private boolean hasAnyPreference(
            NotificationPreference preference) {

        return !preference.getFoodTypes().isEmpty()
                || !preference.getRelatedSubjects().isEmpty()
                || !preference.getBuildingNames().isEmpty()
                || preference.isNoRegistrationOnly();
    }

    private boolean matches(
            FoodEvent event,
            NotificationPreference preference) {

        boolean foodMatches =
                preference.getFoodTypes().isEmpty()
                        || !Collections.disjoint(
                        event.getFoodTypes(),
                        preference.getFoodTypes()
                );

        boolean subjectMatches =
                preference.getRelatedSubjects().isEmpty()
                        || !Collections.disjoint(
                        event.getRelatedSubjects(),
                        preference.getRelatedSubjects()
                );

        boolean buildingMatches =
                preference.getBuildingNames().isEmpty()
                        || preference.getBuildingNames().stream().anyMatch(
                        buildingName ->
                                buildingName.equalsIgnoreCase(
                                        event.getLocation()
                                                .getBuilding()
                                                .getBuildingName()
                                )
                );

        boolean registrationMatches =
                !preference.isNoRegistrationOnly()
                        || !event.isRegistrationRequired();

        return foodMatches
                && subjectMatches
                && buildingMatches
                && registrationMatches;
    }
}
