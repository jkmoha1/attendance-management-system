package com.company.attendance_management_system.service;

import com.company.attendance_management_system.exception.EmployeeNotFoundException;
import com.company.attendance_management_system.exception.InvalidAttendanceException;
import com.company.attendance_management_system.model.AttendanceRecord;
import com.company.attendance_management_system.repository.AttendanceRepository;
import com.company.attendance_management_system.repository.EmployeeRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AttendanceServiceImpl implements AttendanceService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public AttendanceRecord clockIn(int employeeId) throws InvalidAttendanceException, EmployeeNotFoundException {
        requireEmployee(employeeId);

        LocalDate today = LocalDate.now();
        Optional<AttendanceRecord> existing = attendanceRepository.findRecordByDateAndEmployee(employeeId, today);

        if (existing.isPresent()) {
            AttendanceRecord rec = existing.get();
            if (rec.getClockInTime() != null) {
                throw new InvalidAttendanceException("Already clocked in for today.");
            }
            rec.setClockInTime(LocalDateTime.now());
            return rec;
        }

        AttendanceRecord record = attendanceRepository.createRecord(employeeId, today);
        record.setClockInTime(LocalDateTime.now());
        return record;
    }

    @Override
    public AttendanceRecord clockOut(int employeeId) throws InvalidAttendanceException, EmployeeNotFoundException {
        requireEmployee(employeeId);

        LocalDate today = LocalDate.now();
        AttendanceRecord record = attendanceRepository
                .findRecordByDateAndEmployee(employeeId, today)
                .orElseThrow(() -> new InvalidAttendanceException("No clock-in found for today."));

        if (record.getClockInTime() == null) {
            throw new InvalidAttendanceException("Cannot clock out before clocking in.");
        }
        if (record.getClockOutTime() != null) {
            throw new InvalidAttendanceException("Already clocked out for today.");
        }

        LocalDateTime out = LocalDateTime.now();
        record.setClockOutTime(out);

        double hours = Duration.between(record.getClockInTime(), out).toMinutes() / 60.0;
        record.setHoursWorked(Math.round(hours * 100.0) / 100.0);
        return record;
    }

    @Override
    public List<AttendanceRecord> getRecordsForEmployee(int employeeId) {
        return attendanceRepository.findRecordsByEmployeeId(employeeId);
    }

    @Override
    public Optional<AttendanceRecord> getRecordForEmployeeByDate(int employeeId, LocalDate date) {
        return attendanceRepository.findRecordByDateAndEmployee(employeeId, date);
    }

    private void requireEmployee(int employeeId) throws EmployeeNotFoundException {
        employeeRepository.findEmployeeById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + employeeId + " not found."));
    }
}
