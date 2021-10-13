package com.epam.engx.cleancode.errorhandling.task1.exceptions;

public class UserNotHaveSubmittedOrdersException extends RuntimeException {
    public UserNotHaveSubmittedOrdersException(String message) {
        super(message);
    }
}
