package com.company.attendance_management_system.model;

public class Employee {
    // unique id for each employee
    private final int employeeId;  
    private String name;
    private String email;
    private String department;

    // constructor to set employee details
    public Employee(int employeeId, String name, String email, String department) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.department = department;
    }

    // getters and setters
    public int getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    // easy to print employee info
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + employeeId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dept='" + department + '\'' +
                '}';
    }
}
