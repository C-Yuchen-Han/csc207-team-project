package use_case.notification_preferences;

import entity.NotificationPreference;

import java.util.Optional;

public interface NotificationPreferenceRepository {

    void save(NotificationPreference preference);

    Optional<NotificationPreference> findPreference();
}