package com.company.attendance_management_system.service;

import com.company.attendance_management_system.exception.EmployeeNotFoundException;
import com.company.attendance_management_system.exception.InvalidAttendanceException;
import com.company.attendance_management_system.model.AttendanceRecord;
import com.company.attendance_management_system.model.Employee;
import com.company.attendance_management_system.repository.AttendanceRepository;
import com.company.attendance_management_system.repository.EmployeeRepository;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Test(groups = "attendance")
public class AttendanceServiceTest {

    private EmployeeRepository employeeRepository;
    private AttendanceRepository attendanceRepository;
    private AttendanceServiceImpl attendance;
    private EmployeeServiceImpl employees;

    @BeforeMethod
    public void setUp() throws Exception {
        employeeRepository = new EmployeeRepository();
        attendanceRepository = new AttendanceRepository();
        attendance = new AttendanceServiceImpl(employeeRepository, attendanceRepository);
        employees = new EmployeeServiceImpl(employeeRepository);
        employees.addEmployee(new Employee(10, "Test Emp", "testemp@example.com", "IT"));
    }

    @Test(priority = 1)
    public void clockIn_setsRecordForToday() throws Exception {
        AttendanceRecord rec = attendance.clockIn(10);
        Assert.assertNotNull(rec.getClockInTime(), "clock-in time should be set");
        Assert.assertEquals(rec.getDate(), LocalDate.now());
    }

    @Test(priority = 2,
          expectedExceptions = InvalidAttendanceException.class,
          expectedExceptionsMessageRegExp = ".*Already clocked in.*")
    public void secondClockIn_sameDay_throws() throws Exception {
        attendance.clockIn(10); // first time works
        attendance.clockIn(10); // second time should fail
    }

    @Test(priority = 3,
          expectedExceptions = InvalidAttendanceException.class,
          expectedExceptionsMessageRegExp = ".*No clock-in found.*")
    public void clockOut_withoutClockIn_throws() throws Exception {
        attendanceRepository.clear();
        attendance.clockOut(10);
    }

    @Test(priority = 4)
    public void clockIn_thenClockOut_setsHours() throws Exception {
        AttendanceRecord rec = attendance.clockIn(10);
        rec.setClockInTime(LocalDateTime.now().minusHours(2).truncatedTo(ChronoUnit.SECONDS));

        AttendanceRecord out = attendance.clockOut(10);
        Assert.assertNotNull(out.getClockOutTime());
        Assert.assertNotNull(out.getHoursWorked());
        Assert.assertTrue(out.getHoursWorked() >= 1.99 && out.getHoursWorked() <= 2.01,
                "expected ~2.0, got " + out.getHoursWorked());
    }

    @Test(priority = 5,
          expectedExceptions = InvalidAttendanceException.class,
          expectedExceptionsMessageRegExp = ".*Already clocked out.*")
    public void secondClockOut_sameDay_throws() throws Exception {
        attendance.clockIn(10);
        attendance.getRecordForEmployeeByDate(10, LocalDate.now())
                .ifPresent(r -> r.setClockInTime(LocalDateTime.now().minusMinutes(1).truncatedTo(ChronoUnit.SECONDS)));

        attendance.clockOut(10); // first time works
        attendance.clockOut(10); // second time should fail
    }

    @Test(priority = 6, expectedExceptions = EmployeeNotFoundException.class)
    public void clockIn_unknownEmployee_throws() throws Exception {
        attendance.clockIn(9999);
    }

    @DataProvider(name = "durationsHours")
    public Object[][] durationsHours() {
        return new Object[][]{
                {0.25},  // 15 minutes
                {2.0},   // 2 hours
                {7.75}   // 7h 45m
        };
    }

    @Test(priority = 7, dataProvider = "durationsHours")
    public void clockIn_thenClockOut_variousDurations(double hours) throws Exception {
        AttendanceRecord rec = attendance.clockIn(10);

        long minutes = Math.round(hours * 60);
        rec.setClockInTime(LocalDateTime.now().minusMinutes(minutes).truncatedTo(ChronoUnit.SECONDS));

        AttendanceRecord out = attendance.clockOut(10);
        Assert.assertNotNull(out.getHoursWorked());
        Assert.assertEquals(out.getHoursWorked(), hours, 0.02);
    }

    @Test(priority = 8,
          expectedExceptions = InvalidAttendanceException.class,
          expectedExceptionsMessageRegExp = ".*No clock-in found for today.*")
    public void overnightShift_notSupported_documentedBehavior() throws Exception {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        AttendanceRecord r = attendanceRepository.createRecord(10, yesterday);
        r.setClockInTime(LocalDateTime.now().minusDays(1).withHour(23).withMinute(0).withSecond(0).withNano(0));

        attendance.clockOut(10); // should fail because service checks only today's record
    }

    @Test(priority = 9)
    public void getRecordForEmployeeByDate_presentAndAbsent() throws Exception {
        attendance.clockIn(10);

        var todayHit = attendance.getRecordForEmployeeByDate(10, LocalDate.now());
        Assert.assertTrue(todayHit.isPresent());

        var tomorrowMiss = attendance.getRecordForEmployeeByDate(10, LocalDate.now().plusDays(1));
        Assert.assertTrue(tomorrowMiss.isEmpty());
    }
}
