package com.company.attendance_management_system.repository;

import com.company.attendance_management_system.model.AttendanceRecord;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AttendanceRepository {
    // store all attendance records in memory
    private final Map<Integer, AttendanceRecord> records = new HashMap<>();
    // auto generate unique id for records
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    // create new record for employee on a date
    public AttendanceRecord createRecord(int employeeId, LocalDate date) {
        int id = idGenerator.getAndIncrement();
        AttendanceRecord record = new AttendanceRecord(id, employeeId, date);
        records.put(id, record);
        return record;
    }

    // add an existing record
    public void addRecord(AttendanceRecord record) {
        records.put(record.getRecordId(), record);
    }

    // get all records for one employee
    public List<AttendanceRecord> findRecordsByEmployeeId(int employeeId) {
        return records.values().stream()
                .filter(r -> r.getEmployeeId() == employeeId)
                .sorted(Comparator.comparing(AttendanceRecord::getDate))
                .collect(Collectors.toList());
    }

    // find single record by date and employee
    public Optional<AttendanceRecord> findRecordByDateAndEmployee(int employeeId, LocalDate date) {
        return records.values().stream()
                .filter(r -> r.getEmployeeId() == employeeId && r.getDate().equals(date))
                .findFirst();
    }

    // return all attendance records
    public List<AttendanceRecord> getAllRecords() {
        return new ArrayList<>(records.values());
    }

    // clear all records (like reset)
    public void clear() {
        records.clear();
        idGenerator.set(1); // start id again from 1
    }
}
