package com.company.attendance_management_system.repository;

import com.company.attendance_management_system.model.Employee;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

@Test(groups = "repository")
public class EmployeeRepositoryTest {

    private EmployeeRepository repository;

    @BeforeMethod
    public void setUp() {
        repository = new EmployeeRepository();
    }

    @Test
    public void add_thenFindById_returnsEmployee() {
        Employee e = new Employee(100, "Test User", "test@example.com", "HR");
        repository.addEmployee(e);

        Optional<Employee> found = repository.findEmployeeById(100);
        Assert.assertTrue(found.isPresent());
        Assert.assertEquals(found.get().getEmail(), "test@example.com");
    }

    @Test
    public void findByEmail_returnsEmployee() {
        Employee e = new Employee(101, "User Two", "two@example.com", "Ops");
        repository.addEmployee(e);

        Optional<Employee> found = repository.findEmployeeByEmail("two@example.com");
        Assert.assertTrue(found.isPresent());
        Assert.assertEquals(found.get().getEmployeeId(), 101);
    }
}
