package use_case.create_food;

public class CreateFoodOutputData {

    private final String eventId;
    private final String title;

    public CreateFoodOutputData(String eventId, String title) {
        this.eventId = eventId;
        this.title = title;
    }

    public String getEventId() {
        return eventId;
    }

    public String getTitle() {
        return title;
    }
}

