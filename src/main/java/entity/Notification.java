package entity;

import java.time.LocalDateTime;


public class Notification {
    private final String notificationId;
    private final String eventId;
    private final String eventTitle;
    private final LocalDateTime createdAt;
    private boolean isRead;

    public Notification(
            String notificationId,
            String eventId,
            String eventTitle,
            LocalDateTime createdAt) {

        this.notificationId = notificationId;
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.createdAt = createdAt;
        this.isRead = false;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isRead() {
        return isRead;
    }

    public void markAsRead() {
        this.isRead = true;
    }
}
