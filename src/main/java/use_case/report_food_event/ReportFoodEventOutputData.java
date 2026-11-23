
package use_case.report_food_event;

/**
 * Stores the output data after reporting a food event.
 */
public class ReportFoodEventOutputData {

    private final String reportId;
    private final String eventId;

    public ReportFoodEventOutputData(String reportId, String eventId) {
        this.reportId = reportId;
        this.eventId = eventId;
    }

    public String getReportId() {
        return reportId;
    }

    public String getEventId() {
        return eventId;
    }
}
