package com.company.attendance_management_system.repository;

import com.company.attendance_management_system.model.Employee;

import java.util.*;

public class EmployeeRepository {
    // store employees in memory
    private final Map<Integer, Employee> employees = new HashMap<>();

    // add new employee
    public void addEmployee(Employee employee) {
        employees.put(employee.getEmployeeId(), employee);
    }

    // search employee by id
    public Optional<Employee> findEmployeeById(int id) {
        return Optional.ofNullable(employees.get(id));
    }

    // search employee by email
    public Optional<Employee> findEmployeeByEmail(String email) {
        return employees.values().stream()
                .filter(e -> e.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    // get list of all employees
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    // remove all employees (reset)
    public void clear() {
        employees.clear();
    }
}
