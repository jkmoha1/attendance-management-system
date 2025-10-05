package com.company.attendance_management_system.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceRecord {
	// unique id for this record
	private final int recordId;  
	// employee id linked with this record
	private final int employeeId;  
	// date for the attendance
	private final LocalDate date;  
	private LocalDateTime clockInTime;  
	private LocalDateTime clockOutTime;  
	// total hours worked (in decimal)
	private Double hoursWorked;  

	// constructor to create a record
	public AttendanceRecord(int recordId, int employeeId, LocalDate date) {
		this.recordId = recordId;
		this.employeeId = employeeId;
		this.date = date;
	}

	// getters and setters
	public int getRecordId() { return recordId; }
	public int getEmployeeId() { return employeeId; }
	public LocalDate getDate() { return date; }
	public LocalDateTime getClockInTime() { return clockInTime; }
	public void setClockInTime(LocalDateTime clockInTime) { this.clockInTime = clockInTime; }
	public LocalDateTime getClockOutTime() { return clockOutTime; }
	public void setClockOutTime(LocalDateTime clockOutTime) { this.clockOutTime = clockOutTime; }
	public Double getHoursWorked() { return hoursWorked; }
	public void setHoursWorked(Double hoursWorked) { this.hoursWorked = hoursWorked; }

	// to print record in readable format
	@Override
	public String toString() {
		return "AttendanceRecord{" +
				"id=" + recordId +
				", emp=" + employeeId +
				", date=" + date +
				", in=" + clockInTime +
				", out=" + clockOutTime +
				", hours=" + hoursWorked +
				'}';
	}
}
