package com.company.attendance_management_system.service;

import com.company.attendance_management_system.exception.DuplicateEmployeeException;
import com.company.attendance_management_system.model.Employee;
import com.company.attendance_management_system.repository.EmployeeRepository;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

@Test(groups = "employee")
public class EmployeeServiceTest {

    private EmployeeRepository repository;
    private EmployeeServiceImpl service;

    @BeforeMethod
    public void setUp() {
        repository = new EmployeeRepository();
        service = new EmployeeServiceImpl(repository);
    }

    @Test
    public void addEmployee_thenFind_success() throws DuplicateEmployeeException {
        var e = new Employee(1, "Alice", "alice@company.com", "Dev");
        service.addEmployee(e);

        Optional<Employee> out = service.findEmployeeById(1);
        Assert.assertTrue(out.isPresent());
        Assert.assertEquals(out.get().getEmail(), "alice@company.com");
    }

    @Test(expectedExceptions = DuplicateEmployeeException.class)
    public void addDuplicateId_throws() throws DuplicateEmployeeException {
        service.addEmployee(new Employee(2, "Bob", "bob@company.com", "Sales"));
        service.addEmployee(new Employee(2, "Bob2", "bob2@company.com", "Sales"));
    }

    @Test(expectedExceptions = DuplicateEmployeeException.class)
    public void addDuplicateEmail_throws() throws DuplicateEmployeeException {
        service.addEmployee(new Employee(3, "Cathy", "cathy@company.com", "Support"));
        service.addEmployee(new Employee(4, "Cathy2", "cathy@company.com", "Support"));
    }
}
