# Employee Attendance Management System

A clean, test-driven **Java 11 + Maven** sample project that demonstrates
core attendance tracking for employees: add employees, clock-in/clock-out,
track daily hours, request/approve leave, and generate simple reports – all
with in‑memory repositories and unit tests using **TestNG** and **AssertJ**.

> This is a backend, library-style project (no web UI). You can run the unit
> tests or write a small `main` program to try it out.

---

## ✨ Features

- **Employee Management**
  - Add employees (id, name, email, department)
  - Find by id / email, list all employees
  - Duplicate ID protection

- **Attendance Tracking**
  - Clock-in, clock-out with validation
  - Prevents double clock-ins/clock-outs
  - Calculates `hoursWorked` per record (decimal hours)
  - Fetch records by employee and/or date

- **Leave Management**
  - Apply for leave (start/end date + reason)
  - Approve/Reject leave requests
  - List requests for an employee

- **Reporting**
  - Filter attendance by date range
  - Compute monthly hours for an employee (rounded to 2 decimals)

- **Test Suite**
  - Unit tests for repositories & services with **TestNG**
  - Fluent assertions via **AssertJ**
  - Simple logging via **slf4j-simple**

---

## 🧱 Tech Stack

- **Language:** Java 11
- **Build:** Maven
- **Tests:** TestNG, AssertJ
- **Logging:** SLF4J (simple)
- **Storage:** In‑memory Maps (no external DB)

---

## 📂 Project Structure

```
attendance-management-system/
├─ pom.xml
└─ src
   ├─ main
   │  └─ java/com/company/attendance_management_system
   │     ├─ App.java                               # (optional demo entry point)
   │     ├─ exception
   │     │  ├─ DuplicateEmployeeException.java
   │     │  ├─ EmployeeNotFoundException.java
   │     │  └─ InvalidAttendanceException.java
   │     ├─ model
   │     │  ├─ AttendanceRecord.java               # recordId, employeeId, date, in/out, hoursWorked
   │     │  ├─ Employee.java                       # employeeId, name, email, department
   │     │  └─ LeaveRequest.java                   # requestId, employeeId, start/end, status, reason
   │     ├─ repository
   │     │  ├─ AttendanceRepository.java           # in-memory attendance store
   │     │  └─ EmployeeRepository.java             # in-memory employee store
   │     └─ service
   │        ├─ AttendanceService.java / Impl       # clockIn/clockOut, queries
   │        ├─ EmployeeService.java / Impl         # add/find/list employees
   │        ├─ LeaveService.java / Impl            # apply/approve/reject/list
   │        └─ ReportService.java                  # date-range & monthly hours
   └─ test
      └─ java/com/company/attendance_management_system
         ├─ repository/EmployeeRepositoryTest.java
         └─ service
            ├─ AttendanceServiceTest.java
            ├─ EmployeeServiceTest.java
            ├─ LeaveServiceTest.java
            └─ ReportServiceTest.java
```

> Note: All data is kept in memory for simplicity and fast tests.

---

## 🚀 Getting Started

### Prerequisites
- **Java 11** (JDK 11)
- **Maven 3.8+**

### Build
```bash
mvn -q -e -DskipTests package
```

### Run Tests
```bash
mvn test
```
You’ll see TestNG executing the suites under `src/test/java/...`.

### Try It Quickly (Sample `main`)
You can run the included `App.java` or use the snippet below to experiment:

```java
import com.company.attendance_management_system.model.Employee;
import com.company.attendance_management_system.service.*;
import com.company.attendance_management_system.repository.*;
import com.company.attendance_management_system.exception.*;

import java.time.LocalDate;
import java.time.YearMonth;

public class Demo {
    public static void main(String[] args) throws Exception {
        // repositories
        var empRepo = new EmployeeRepository();
        var attRepo = new AttendanceRepository();

        // services
        var employeeService   = new EmployeeServiceImpl(empRepo);
        var attendanceService = new AttendanceServiceImpl(attRepo, empRepo);
        var leaveService      = new LeaveServiceImpl(empRepo);
        var reportService     = new ReportService(attendanceService);

        // employees
        employeeService.addEmployee(new Employee(1, "Adeena", "adeena@amazon.com", "QA"));
        employeeService.addEmployee(new Employee(2, "Musthafa",   "musthafa@amazon.com",   "Eng"));

        // attendance
        attendanceService.clockIn(1);
        // ...do work...
        attendanceService.clockOut(1);

        // leave
        leaveService.applyForLeave(2, LocalDate.now().toString(), LocalDate.now().plusDays(2).toString(), "Family");
        leaveService.approveLeave(1, true);

        // reports
        var month = YearMonth.now();
        double hours = reportService.calculateMonthlyHours(1, month);
        System.out.println("Alice hours in " + month + ": " + hours);
    }
}
```

Compile & run the demo with Maven (one-off):
```bash
mvn -q -Dexec.mainClass=Demo \
    -Dexec.cleanupDaemonThreads=false \
    org.codehaus.mojo:exec-maven-plugin:3.1.0:java
```
> Or set your IDE run configuration to a class with a `main` method.

---

## 🧩 Key Concepts & Validations

### Employee
- Uniqueness enforced by `employeeId`
- `DuplicateEmployeeException` thrown when adding an existing id

### Attendance
- `clockIn(employeeId)` creates a record for **today** if not present; rejects if already clocked-in today
- `clockOut(employeeId)` completes today’s open record and computes decimal hours using `Duration`
- Validations:
  - `EmployeeNotFoundException` if employee id doesn’t exist
  - `InvalidAttendanceException` for double clock-ins/outs or missing open record

### Leave
- `applyForLeave(employeeId, startDateIso, endDateIso, reason)`
  - Validates employee exists and `startDate <= endDate`
  - Auto‑assigns request IDs and defaults status to `PENDING`
- `approveLeave(requestId, approve)` → sets `APPROVED` or `REJECTED`

### Reporting
- `getRecordsInRange(employeeId, LocalDate start, LocalDate end)`
- `calculateMonthlyHours(employeeId, YearMonth month)` → sum of `hoursWorked`

---

## 🔌 Public APIs (Services)

```java
// EmployeeService
void addEmployee(Employee e) throws DuplicateEmployeeException;
Optional<Employee> findEmployeeById(int id);
List<Employee> getAllEmployees();

// AttendanceService
AttendanceRecord clockIn(int employeeId)
  throws InvalidAttendanceException, EmployeeNotFoundException;
AttendanceRecord clockOut(int employeeId)
  throws InvalidAttendanceException, EmployeeNotFoundException;
List<AttendanceRecord> getRecordsForEmployee(int employeeId);
Optional<AttendanceRecord> getRecordForEmployeeByDate(int employeeId, LocalDate date);

// LeaveService
LeaveRequest applyForLeave(int employeeId, String startISO, String endISO, String reason)
  throws EmployeeNotFoundException, IllegalArgumentException;
void approveLeave(int requestId, boolean approve);
List<LeaveRequest> getLeaveRequestsForEmployee(int employeeId);

// ReportService
List<AttendanceRecord> getRecordsInRange(int employeeId, LocalDate start, LocalDate end);
double calculateMonthlyHours(int employeeId, YearMonth month);
```

---

## 🧪 Testing

- Located under `src/test/java/...`
- Run with `mvn test`
- Covers:
  - Employee add/find/list
  - Clock-in/out and edge cases
  - Leave application & approval
  - Reporting by date-range & month

Example:
```bash
mvn -Dtest=AttendanceServiceTest test
```

---

## 🛠️ Configuration

- No external configuration is required.
- Java version is controlled via Maven compiler properties:
  ```xml
  <maven.compiler.source>11</maven.compiler.source>
  <maven.compiler.target>11</maven.compiler.target>
  ```
- Logging uses `slf4j-simple` (no config needed).

---

## 🗺️ Extending the Project

- **Swap storage**: Replace in-memory repositories with JPA/JDBC implementations.
- **Add REST API**: Introduce Spring Boot controllers & DTOs.
- **Policies**: Add attendance/leave policies (holidays, weekly hours, overtime).
- **Persistence**: Introduce DAO layer and DB migrations (Liquibase/Flyway).
- **Auth**: Wire employee data to an identity provider.

---
