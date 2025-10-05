package com.company.attendance_management_system.service;

import com.company.attendance_management_system.exception.DuplicateEmployeeException;
import com.company.attendance_management_system.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void addEmployee(Employee employee) throws DuplicateEmployeeException;
    Optional<Employee> findEmployeeById(int id);
    List<Employee> getAllEmployees();
}
