package data_access;

import entity.NotificationPreference;
import use_case.notification_preferences.NotificationPreferenceRepository;

import java.util.Optional;

public class InMemoryNotificationPreferenceRepository
        implements NotificationPreferenceRepository {

    private NotificationPreference preference;

    @Override
    public void save(NotificationPreference preference) {
        this.preference = preference;
    }

    @Override
    public Optional<NotificationPreference> findPreference() {
        return Optional.ofNullable(preference);
    }
}