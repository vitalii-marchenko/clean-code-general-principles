package com.epam.engx.cleancode.errorhandling.task1.exceptions;

public class UserDaoNotInitializedException extends RuntimeException {
    public UserDaoNotInitializedException(String message) {
        super(message);
    }
}
