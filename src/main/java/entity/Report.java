package entity;

import java.time.LocalDateTime;

/**
 * Represents a report submitted for a food event.
 */
public class Report {

    private final String reportId;
    private final String eventId;
    private final ReportReason reason;
    private final String customReason;
    private final LocalDateTime reportedAt;

    public Report(
            String reportId,
            String eventId,
            ReportReason reason,
            String customReason,
            LocalDateTime reportedAt) {

        this.reportId = reportId;
        this.eventId = eventId;
        this.reason = reason;
        this.customReason = customReason;
        this.reportedAt = reportedAt;
    }

    public String getReportId() {
        return reportId;
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

    public LocalDateTime getReportedAt() {
        return reportedAt;
    }
}
