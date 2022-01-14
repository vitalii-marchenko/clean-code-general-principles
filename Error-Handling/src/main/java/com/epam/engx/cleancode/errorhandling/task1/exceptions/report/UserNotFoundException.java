package com.epam.engx.cleancode.errorhandling.task1.exceptions.report;

public class UserNotFoundException extends UserReportException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
