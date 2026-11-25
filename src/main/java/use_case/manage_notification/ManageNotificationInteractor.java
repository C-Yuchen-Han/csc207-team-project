package use_case.manage_notification;

import entity.Notification;

import java.util.List;
import java.util.Optional;

public class ManageNotificationInteractor {

    private final NotificationRepository repository;

    public ManageNotificationInteractor(
            NotificationRepository repository) {
        this.repository = repository;
    }

    // get all notification
    public List<Notification> getNotifications() {
        return repository.findAll();
    }
    // mark notification as read

    public void markAsRead(String notificationId) {

        Optional<Notification> notification =
                repository.findById(notificationId);

        if (notification.isPresent()) {
            notification.get().markAsRead();
            repository.update(notification.get());
        }
    }
    // delete notification

    public void deleteNotification(String notificationId) {
        repository.deleteById(notificationId);
    }
}