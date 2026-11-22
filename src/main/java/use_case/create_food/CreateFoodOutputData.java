package use_case.create_food;

/**
 * Stores the result of creating a food event
 */
public class CreateFoodOutputData {

    private final String eventId;
    private final String title;

    /**
     * Constructs a CreateFoodOutputData object.
     *
     * @param eventId the unique identifier of the event
     * @param title the title of the event
     */
    public CreateFoodOutputData(String eventId, String title) {
        this.eventId = eventId;
        this.title = title;
    }

    /**
     * Returns the unique identifier of the event.
     *
     * @return the event identifier
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Returns the title of the event.
     *
     * @return the event title
     */
    public String getTitle() {
        return title;
    }
}

