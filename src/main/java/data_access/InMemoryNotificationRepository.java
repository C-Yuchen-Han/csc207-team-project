package data_access;

import entity.Notification;
import use_case.manage_notification.NotificationRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InMemoryNotificationRepository
        implements NotificationRepository {

    private final List<Notification> notifications = new ArrayList<>();

    @Override
    public void save(Notification notification) {
        notifications.add(notification);
    }

    @Override
    public List<Notification> findAll() {
        return new ArrayList<>(notifications);
    }

    @Override
    public Optional<Notification> findById(String notificationId) {

        for (Notification notification : notifications) {
            if (notification.getNotificationId().equals(notificationId)) {
                return Optional.of(notification);
            }
        }

        return Optional.empty();
    }

    @Override
    public void update(Notification notification) {

        for (int i = 0; i < notifications.size(); i++) {

            if (notifications.get(i).getNotificationId()
                    .equals(notification.getNotificationId())) {

                notifications.set(i, notification);
                return;
            }
        }
    }

    @Override
    public void deleteById(String notificationId) {

        notifications.removeIf(
                notification ->
                        notification.getNotificationId().equals(notificationId)
        );
    }
}