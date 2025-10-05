package com.company.attendance_management_system.model;

import java.time.LocalDate;

public class LeaveRequest {
    // status of leave -> pending/approved/rejected
    public enum Status { PENDING, APPROVED, REJECTED }

    // unique id for leave request
    private final int requestId;
    // employee who applied
    private final int employeeId;
    // leave start and end date
    private final LocalDate startDate;
    private final LocalDate endDate;
    // current status of request
    private Status status;
    // reason for leave
    private final String reason;

    // constructor - set values while creating leave request
    public LeaveRequest(int requestId, int employeeId, LocalDate startDate, LocalDate endDate, String reason) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = Status.PENDING; // by default new request is pending
        this.reason = reason;
    }

    // getters and setters
    public int getRequestId() { return requestId; }
    public int getEmployeeId() { return employeeId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public String getReason() { return reason; }

    // to print leave request details
    @Override
    public String toString() {
        return "LeaveRequest{" +
                "id=" + requestId +
                ", emp=" + employeeId +
                ", " + startDate + "->" + endDate +
                ", status=" + status +
                ", reason='" + reason + '\'' +
                '}';
    }
}
