package com.company.attendance_management_system.service;

import com.company.attendance_management_system.exception.EmployeeNotFoundException;
import com.company.attendance_management_system.model.Employee;
import com.company.attendance_management_system.model.LeaveRequest;
import com.company.attendance_management_system.repository.EmployeeRepository;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

@Test(groups = "leave")
public class LeaveServiceTest {

    private EmployeeRepository repo;
    private LeaveServiceImpl leave;
    private EmployeeServiceImpl employees;

    @BeforeMethod
    public void setUp() throws Exception {
        repo = new EmployeeRepository();
        leave = new LeaveServiceImpl(repo);
        employees = new EmployeeServiceImpl(repo);
        employees.addEmployee(new Employee(20, "Leave Emp", "leave@example.com", "Finance"));
    }

    @DataProvider(name = "ranges")
    public Object[][] ranges() {
        return new Object[][]{
                {"2024-01-15", "2024-01-17", true},
                {"2024-01-20", "2024-01-15", false},
                {"2024-02-01", "2024-02-01", true}
        };
    }

    @Test(dataProvider = "ranges", priority = 1)
    public void apply_variousRanges_behavesAsExpected(String start, String end, boolean ok) {
        boolean success = true;
        try {
            LeaveRequest r = leave.applyForLeave(20, start, end, "test");
            Assert.assertNotNull(r);
        } catch (IllegalArgumentException | EmployeeNotFoundException ex) {
            success = false;
        }
        Assert.assertEquals(success, ok);
    }

    @Test(priority = 2, expectedExceptions = EmployeeNotFoundException.class)
    public void apply_unknownEmployee_throws() throws Exception {
        leave.applyForLeave(999, "2024-01-01", "2024-01-02", "x");
    }

    @Test(priority = 3)
    public void approve_updatesStatus() throws Exception {
        LeaveRequest r = leave.applyForLeave(20, "2024-03-01", "2024-03-05", "vac");
        Assert.assertEquals(r.getStatus(), LeaveRequest.Status.PENDING);
        leave.approveLeave(r.getRequestId(), true);
        List<LeaveRequest> list = leave.getLeaveRequestsForEmployee(20);
        Assert.assertEquals(list.get(0).getStatus(), LeaveRequest.Status.APPROVED);
    }

    @Test(priority = 4)
    public void reject_updatesStatus() throws Exception {
        LeaveRequest r = leave.applyForLeave(20, "2024-04-10", "2024-04-12", "pers");
        leave.approveLeave(r.getRequestId(), false);
        List<LeaveRequest> list = leave.getLeaveRequestsForEmployee(20);
        Assert.assertTrue(list.stream().anyMatch(x -> x.getRequestId() == r.getRequestId()
                && x.getStatus() == LeaveRequest.Status.REJECTED));
    }
}
