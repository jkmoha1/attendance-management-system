package com.company.attendance_management_system.service;

import com.company.attendance_management_system.exception.EmployeeNotFoundException;
import com.company.attendance_management_system.model.LeaveRequest;

import java.util.List;

public interface LeaveService {
    LeaveRequest applyForLeave(int employeeId, String startDateIso, String endDateIso, String reason)
            throws EmployeeNotFoundException, IllegalArgumentException;

    void approveLeave(int requestId, boolean approve);

    List<LeaveRequest> getLeaveRequestsForEmployee(int employeeId);
}
