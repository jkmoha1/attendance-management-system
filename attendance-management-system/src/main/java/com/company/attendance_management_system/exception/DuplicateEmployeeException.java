package com.company.attendance_management_system.exception;

@SuppressWarnings("serial")
public class DuplicateEmployeeException extends Exception {
    public DuplicateEmployeeException(String message) {
        super(message);
    }
}
