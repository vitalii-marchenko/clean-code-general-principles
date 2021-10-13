package com.epam.engx.cleancode.errorhandling.task1.exceptions;

public class WrongOrderAmountException extends RuntimeException {
    public WrongOrderAmountException(String message) {
        super(message);
    }
}
