package com.company.attendance_management_system.service;

import com.company.attendance_management_system.exception.EmployeeNotFoundException;
import com.company.attendance_management_system.model.LeaveRequest;
import com.company.attendance_management_system.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LeaveServiceImpl implements LeaveService {

    private final EmployeeRepository employeeRepository;
    private final Map<Integer, LeaveRequest> leaveStore = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public LeaveServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public LeaveRequest applyForLeave(int employeeId, String startDateIso, String endDateIso, String reason)
            throws EmployeeNotFoundException, IllegalArgumentException {

        employeeRepository.findEmployeeById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + employeeId + " not found."));

        LocalDate start = LocalDate.parse(startDateIso);
        LocalDate end = LocalDate.parse(endDateIso);
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End date must be on or after start date.");
        }

        int id = idGenerator.getAndIncrement();
        LeaveRequest request = new LeaveRequest(id, employeeId, start, end, reason);
        leaveStore.put(id, request);
        return request;
    }

    @Override
    public void approveLeave(int requestId, boolean approve) {
        LeaveRequest req = leaveStore.get(requestId);
        if (req == null) return;
        req.setStatus(approve ? LeaveRequest.Status.APPROVED : LeaveRequest.Status.REJECTED);
    }

    @Override
    public List<LeaveRequest> getLeaveRequestsForEmployee(int employeeId) {
        List<LeaveRequest> list = new ArrayList<>();
        for (LeaveRequest r : leaveStore.values()) {
            if (r.getEmployeeId() == employeeId) list.add(r);
        }
        list.sort(Comparator.comparing(LeaveRequest::getStartDate));
        return list;
    }
}
