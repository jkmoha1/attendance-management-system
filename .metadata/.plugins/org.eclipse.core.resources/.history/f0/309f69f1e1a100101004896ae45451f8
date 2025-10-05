package com.company.attendance_management_system.service;

import com.company.attendance_management_system.model.AttendanceRecord;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

public class ReportService {

    private final AttendanceService attendanceService;

    public ReportService(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    public List<AttendanceRecord> generateAttendanceReport(int employeeId, LocalDate startDate, LocalDate endDate) {
        return attendanceService.getRecordsForEmployee(employeeId).stream()
                .filter(r -> !r.getDate().isBefore(startDate) && !r.getDate().isAfter(endDate))
                .collect(Collectors.toList());
    }

    public double calculateMonthlyHours(int employeeId, YearMonth month) {
        double sum = 0.0;
        for (var r : attendanceService.getRecordsForEmployee(employeeId)) {
            if (YearMonth.from(r.getDate()).equals(month) && r.getHoursWorked() != null) {
                sum += r.getHoursWorked();
            }
        }
        return Math.round(sum * 100.0) / 100.0;
    }
}
