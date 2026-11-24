package use_case.manage_notification;

import entity.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository {
    void save(Notification notification);

    List<Notification> findAll();

    Optional<Notification> findById(String notificationId);

    void update(Notification notification);

    void deleteById(String notificationId);
}
