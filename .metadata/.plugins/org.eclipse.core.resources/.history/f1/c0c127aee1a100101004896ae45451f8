package com.company.attendance_management_system.service;

import com.company.attendance_management_system.exception.DuplicateEmployeeException;
import com.company.attendance_management_system.model.Employee;
import com.company.attendance_management_system.repository.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addEmployee(Employee employee) throws DuplicateEmployeeException {
        if (repository.findEmployeeById(employee.getEmployeeId()).isPresent()) {
            throw new DuplicateEmployeeException("ID already exists: " + employee.getEmployeeId());
        }
        if (repository.findEmployeeByEmail(employee.getEmail()).isPresent()) {
            throw new DuplicateEmployeeException("Email already exists: " + employee.getEmail());
        }
        repository.addEmployee(employee);
    }

    @Override
    public java.util.Optional<Employee> findEmployeeById(int id) {
        return repository.findEmployeeById(id);
    }

    @Override
    public java.util.List<Employee> getAllEmployees() {
        return repository.getAllEmployees();
    }
}

