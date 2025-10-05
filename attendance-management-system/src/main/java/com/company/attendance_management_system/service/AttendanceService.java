package com.company.attendance_management_system.service;

import com.company.attendance_management_system.exception.EmployeeNotFoundException;
import com.company.attendance_management_system.exception.InvalidAttendanceException;
import com.company.attendance_management_system.model.AttendanceRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceService {
    AttendanceRecord clockIn(int employeeId) throws InvalidAttendanceException, EmployeeNotFoundException;
    AttendanceRecord clockOut(int employeeId) throws InvalidAttendanceException, EmployeeNotFoundException;
    List<AttendanceRecord> getRecordsForEmployee(int employeeId);
    Optional<AttendanceRecord> getRecordForEmployeeByDate(int employeeId, LocalDate date);
}
