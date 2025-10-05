package com.company.attendance_management_system;

import com.company.attendance_management_system.exception.DuplicateEmployeeException;
import com.company.attendance_management_system.exception.EmployeeNotFoundException;
import com.company.attendance_management_system.exception.InvalidAttendanceException;
import com.company.attendance_management_system.model.AttendanceRecord;
import com.company.attendance_management_system.model.Employee;
import com.company.attendance_management_system.model.LeaveRequest;
import com.company.attendance_management_system.repository.AttendanceRepository;
import com.company.attendance_management_system.repository.EmployeeRepository;
import com.company.attendance_management_system.service.*;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {

    private final EmployeeRepository employeeRepo = new EmployeeRepository();
    private final AttendanceRepository attendanceRepo = new AttendanceRepository();

    private final EmployeeService employees = new EmployeeServiceImpl(employeeRepo);
    private final AttendanceService attendance = new AttendanceServiceImpl(employeeRepo, attendanceRepo);
    private final LeaveService leaves = new LeaveServiceImpl(employeeRepo);
    private final ReportService reports = new ReportService(attendance);

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                printMenu();
                String pick = in.nextLine().trim();
                try {
                    switch (pick) {
                        case "1": addEmployee(in); break;
                        case "2": doClockIn(in); break;
                        case "3": doClockOut(in); break;
                        case "4": applyLeave(in); break;
                        case "5": updateLeaveStatus(in); break;
                        case "6": showTodayRecord(in); break;
                        case "7": showMonthlyHours(in); break;
                        case "8": listEmployees(); break;
                        case "9": listEmployeeLeaves(in); break;
                        case "10": showAttendanceReport(in); break;
                        case "0": System.out.println("Bye."); return;
                        default: System.out.println("Unknown option.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("=== Attendance Management System ===");
        System.out.println("1) Add employee");
        System.out.println("2) Clock in");
        System.out.println("3) Clock out");
        System.out.println("4) Apply leave");
        System.out.println("5) Approve/Reject leave");
        System.out.println("6) Today’s record");
        System.out.println("7) Monthly hours");
        System.out.println("8) List employees");
        System.out.println("9) List employee leaves");
        System.out.println("10) Attendance report (date range)");
        System.out.println("0) Exit");
        System.out.print("Choose: ");
    }

    private void addEmployee(Scanner in) throws DuplicateEmployeeException {
        int id = askInt(in, "Employee ID: ");
        String name = ask(in, "Name: ");
        String email = ask(in, "Email: ");
        String dept = ask(in, "Department: ");
        employees.addEmployee(new Employee(id, name, email, dept));
        System.out.println("Saved.");
    }

    private void doClockIn(Scanner in) throws InvalidAttendanceException, EmployeeNotFoundException {
        int id = ensureEmployee(in);
        AttendanceRecord r = attendance.clockIn(id);
        System.out.println("Clocked in: " + r.getClockInTime());
    }

    private void doClockOut(Scanner in) throws InvalidAttendanceException, EmployeeNotFoundException {
        int id = ensureEmployee(in);
        AttendanceRecord r = attendance.clockOut(id);
        System.out.println("Clocked out: " + r.getClockOutTime() + " | Hours: " + r.getHoursWorked());
    }

    private void applyLeave(Scanner in) throws EmployeeNotFoundException {
        int id = ensureEmployee(in);
        String start = ask(in, "Start date (YYYY-MM-DD): ");
        String end = ask(in, "End date (YYYY-MM-DD): ");
        String reason = ask(in, "Reason: ");
        LeaveRequest req = leaves.applyForLeave(id, start, end, reason);
        System.out.println("Leave requested. ID: " + req.getRequestId() + " | " + req.getStatus());
    }

    private void updateLeaveStatus(Scanner in) {
        int rid = askInt(in, "Leave Request ID: ");
        String yn = ask(in, "Approve? (y/n): ");
        boolean approve = yn.equalsIgnoreCase("y");
        leaves.approveLeave(rid, approve);
        System.out.println("Leave updated.");
    }

    private void showTodayRecord(Scanner in) throws EmployeeNotFoundException {
        int id = ensureEmployee(in);
        Optional<AttendanceRecord> rec = attendance.getRecordForEmployeeByDate(id, java.time.LocalDate.now());
        System.out.println(rec.map(Object::toString).orElse("No record for today."));
    }

    private void showMonthlyHours(Scanner in) throws EmployeeNotFoundException {
        int id = ensureEmployee(in);
        YearMonth ym = askYearMonth(in, "Year-Month (YYYY-MM): ");
        double hours = reports.calculateMonthlyHours(id, ym);
        System.out.println("Total hours for " + ym + ": " + hours);
    }

    private void listEmployees() {
        List<Employee> list = employees.getAllEmployees();
        if (list.isEmpty()) {
            System.out.println("No employees.");
            return;
        }
        list.forEach(e -> System.out.println(
                e.getEmployeeId() + " | " + e.getName() + " | " + e.getEmail() + " | " + e.getDepartment()));
    }

    private void listEmployeeLeaves(Scanner in) throws EmployeeNotFoundException {
        int id = ensureEmployee(in);
        List<LeaveRequest> list = leaves.getLeaveRequestsForEmployee(id);
        if (list.isEmpty()) {
            System.out.println("No leave requests.");
            return;
        }
        list.forEach(r -> System.out.println(
                "#" + r.getRequestId() + " | " + r.getStartDate() + " -> " + r.getEndDate() + " | " + r.getStatus() + " | " + r.getReason()));
    }

    private String ask(Scanner in, String prompt) {
        System.out.print(prompt);
        return in.nextLine().trim();
    }

    private int askInt(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Enter a number.");
            }
        }
    }

    private YearMonth askYearMonth(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim();
            try {
                return YearMonth.parse(s);
            } catch (Exception e) {
                System.out.println("Format should be YYYY-MM.");
            }
        }
    }

    private int ensureEmployee(Scanner in) throws EmployeeNotFoundException {
        int id = askInt(in, "Employee ID: ");
        if (!employees.findEmployeeById(id).isPresent()) {
            throw new EmployeeNotFoundException("Employee " + id + " not found.");
        }
        return id;
    }
    private void showAttendanceReport(Scanner in) throws EmployeeNotFoundException {
        int id = ensureEmployee(in);
        String startStr = ask(in, "Start date (YYYY-MM-DD): ");
        String endStr = ask(in, "End date (YYYY-MM-DD): ");
        java.time.LocalDate start;
        java.time.LocalDate end;
        try {
            start = java.time.LocalDate.parse(startStr);
            end = java.time.LocalDate.parse(endStr);
        } catch (Exception e) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
            return;
        }
        if (end.isBefore(start)) {
            System.out.println("End date must be on or after start date.");
            return;
        }
        var list = reports.generateAttendanceReport(id, start, end);
        if (list.isEmpty()) {
            System.out.println("No attendance records for the selected range.");
            return;
        }
        list.forEach(r -> System.out.println(
                "#" + r.getRecordId()
                + " | " + r.getDate()
                + " | in=" + r.getClockInTime()
                + " | out=" + r.getClockOutTime()
                + " | hours=" + r.getHoursWorked()
        ));
    }

}
