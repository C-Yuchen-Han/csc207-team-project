
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


/**
 * Generates notifications when newly published food events match
 * the user's saved notification preferences.
 *
 * The interactor checks whether preferences have been saved,
 * whether at least one preference is selected, and whether the
 * food event satisfies all selected preference categories.
 */

public class GenerateNotificationInteractor {

    private final NotificationPreferenceRepository preferenceRepository;
    private final NotificationRepository notificationRepository;

    /**
     * Constructs a GenerateNotificationInteractor with the required repositories.
     *
     * @param preferenceRepository the repository used to retrieve saved notification preferences
     * @param notificationRepository the repository used to save generated notifications
     */

    public GenerateNotificationInteractor(
            NotificationPreferenceRepository preferenceRepository,
            NotificationRepository notificationRepository) {

        this.preferenceRepository = preferenceRepository;
        this.notificationRepository = notificationRepository;
    }

    /**
     * Generates and saves a notification if the given food event
     * matches the user's saved notification preferences.
     *
     * No notification is generated if no preferences have been saved,
     * if all preference options are empty, or if the event does not match.
     *
     * @param event the newly published food event to check against the saved preferences
     */

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
                event,
                LocalDateTime.now()
        );

        notificationRepository.save(notification);
    }

    /**
     * Checks whether at least one notification preference has been selected.
     *
     * @param preference the notification preferences to check
     * @return true if at least one preference is selected, false otherwise
     */

    private boolean hasAnyPreference(
            NotificationPreference preference) {

        return !preference.getFoodTypes().isEmpty()
                || !preference.getRelatedSubjects().isEmpty()
                || !preference.getBuildingNames().isEmpty()
                || preference.isNoRegistrationOnly();
    }

    /**
     * Checks whether a food event matches all selected preference categories.
     *
     * Within each category, matching any selected option is sufficient.
     * Categories with no selected options do not impose a restriction.
     *
     * @param event the food event being checked
     * @param preference the user's saved notification preferences
     * @return true if the event matches all selected preference categories,
     *         false otherwise
     */

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
