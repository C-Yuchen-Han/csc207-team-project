package use_case.notification_preferences;

import entity.NotificationPreference;

import java.util.Optional;

public class NotificationPreferenceInteractor {

    private final NotificationPreferenceRepository repository;

    public NotificationPreferenceInteractor(
            NotificationPreferenceRepository repository) {
        this.repository = repository;
    }

    public void savePreferences(NotificationPreference preference) {
        repository.save(preference);
    }

    public Optional<NotificationPreference> getPreferences() {
        return repository.findPreference();
    }
}