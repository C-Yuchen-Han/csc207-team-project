package entity;

import java.time.LocalDateTime;

/**
 * Represents a notification generated when a newly published food event
 * matches the user's saved notification preferences.
 *
 * A notification stores the associated food event, the time the notification
 * was created, and whether the notification has been read.
 */
public class Notification {

    private final String notificationId;
    private final FoodEvent foodEvent;
    private final LocalDateTime createdAt;
    private boolean isRead;

    /**
     * Constructs a Notification associated with a food event.
     *
     * @param notificationId the unique identifier of the notification
     * @param foodEvent the food event associated with this notification
     * @param createdAt the date and time when the notification was created
     */
    public Notification(
            String notificationId,
            FoodEvent foodEvent,
            LocalDateTime createdAt) {

        this.notificationId = notificationId;
        this.foodEvent = foodEvent;
        this.createdAt = createdAt;
        this.isRead = false;
    }

    /**
     * Returns the unique identifier of this notification.
     *
     * @return the notification ID
     */
    public String getNotificationId() {
        return notificationId;
    }

    /**
     * Returns the food event associated with this notification.
     *
     * @return the associated food event
     */
    public FoodEvent getFoodEvent() {
        return foodEvent;
    }

    /**
     * Returns the date and time when this notification was created.
     *
     * @return the notification creation time
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Checks whether this notification has been read.
     *
     * @return true if the notification has been read, false otherwise
     */
    public boolean isRead() {
        return isRead;
    }

    /**
     * Marks this notification as read.
     */
    public void markAsRead() {
        this.isRead = true;
    }
}