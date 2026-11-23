package use_case.report_food_event;

import entity.ReportReason;

/**
 * Stores the input data for reporting a food event.
 */
public class ReportFoodEventInputData {

    private final String eventId;
    private final ReportReason reason;
    private final String customReason;

    public ReportFoodEventInputData(
            String eventId,
            ReportReason reason,
            String customReason) {
        this.eventId = eventId;
        this.reason = reason;
        this.customReason = customReason;
    }

    public String getEventId() {
        return eventId;
    }

    public ReportReason getReason() {
        return reason;
    }

    public String getCustomReason() {
        return customReason;
    }
}

