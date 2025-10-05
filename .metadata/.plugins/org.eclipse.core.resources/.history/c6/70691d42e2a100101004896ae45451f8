package com.company.attendance_management_system.service;

import com.company.attendance_management_system.model.AttendanceRecord;
import com.company.attendance_management_system.model.Employee;
import com.company.attendance_management_system.repository.AttendanceRepository;
import com.company.attendance_management_system.repository.EmployeeRepository;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Test(groups = "reporting")
public class ReportServiceTest {

    private EmployeeRepository er;
    private AttendanceRepository ar;
    private AttendanceServiceImpl attendance;
    private EmployeeServiceImpl employees;
    private ReportService reports;

    @BeforeMethod
    public void setUp() throws Exception {
        er = new EmployeeRepository();
        ar = new AttendanceRepository();
        attendance = new AttendanceServiceImpl(er, ar);
        employees = new EmployeeServiceImpl(er);
        reports = new ReportService(attendance);

        employees.addEmployee(new Employee(50, "Rep User", "rep@ex.com", "Ops"));

        LocalDate d1 = LocalDate.now().withDayOfMonth(1);
        LocalDate d2 = d1.plusDays(1);
        LocalDate d3 = d1.plusDays(2);

        AttendanceRecord r1 = ar.createRecord(50, d1);
        r1.setClockInTime(LocalDateTime.now().minusDays(10).truncatedTo(ChronoUnit.SECONDS));
        r1.setClockOutTime(r1.getClockInTime().plusHours(2));
        r1.setHoursWorked(2.0);

        AttendanceRecord r2 = ar.createRecord(50, d2);
        r2.setClockInTime(LocalDateTime.now().minusDays(9).truncatedTo(ChronoUnit.SECONDS));
        r2.setClockOutTime(r2.getClockInTime().plusMinutes(210));
        r2.setHoursWorked(3.5);

        AttendanceRecord r3 = ar.createRecord(50, d3);
        r3.setClockInTime(LocalDateTime.now().minusDays(8).truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    public void report_filtersByRange() {
        LocalDate start = LocalDate.now().withDayOfMonth(1);
        LocalDate end = start.plusDays(1);
        List<AttendanceRecord> subset = reports.generateAttendanceReport(50, start, end);
        Assert.assertEquals(subset.size(), 2);
        Assert.assertTrue(subset.stream().allMatch(r ->
                !r.getDate().isBefore(start) && !r.getDate().isAfter(end)
        ));
    }

    @Test(dependsOnMethods = "report_filtersByRange")
    public void monthlyHours_sumsWithRounding() {
        double total = reports.calculateMonthlyHours(50, YearMonth.now());
        Assert.assertEquals(total, 5.5, 0.001);
    }
}
