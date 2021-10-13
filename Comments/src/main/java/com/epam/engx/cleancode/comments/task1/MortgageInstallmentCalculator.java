package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {

    private static final int MONTHS_IN_YEAR = 12;
    private static final int MIN_ALLOWED_INPUT = 0;

    public static double calculateMonthlyPayment(LoanApplication application) {
        validateAndConvertToStandardizedForm(application);
        return calculatePayment(application);
    }

    private static void validateAndConvertToStandardizedForm(LoanApplication application) {
        validateApplication(application);
        convertToStandardizedForm(application);
    }

    private static double calculatePayment(LoanApplication application) {
        if (isZeroInterest(application.getInterest())) {
            return calculateMonthlyPaymentWithZeroInterest(application);
        }
        return calculateMonthlyPaymentWithNonZeroInterest(application);
    }

    private static double calculateMonthlyPaymentWithZeroInterest(LoanApplication application) {
        return (double) application.getLoanAmount() / application.getLoanDuration();
    }

    /*
    Formula for calculating monthly payment
    P = L * (I / (1 - (1 + I)**-M))
    where:
    P - monthly payment
    L - loan amount
    I - monthly interest rate
    M - months before credit end date
     */
    private static double calculateMonthlyPaymentWithNonZeroInterest(LoanApplication application) {
        double monthlyInterest = application.getInterest();
        double numerator = application.getLoanAmount() * monthlyInterest;
        double denominator = 1 - Math.pow(1 + monthlyInterest, (-1) * application.getLoanDuration());
        return numerator / denominator;
    }

    private static boolean isZeroInterest(double interest) {
        return interest == MIN_ALLOWED_INPUT;
    }

    private static void convertToStandardizedForm(LoanApplication application) {
        application.setLoanAmount(application.getLoanAmount());
        application.setLoanDuration(covertToMonths(application.getLoanDuration()));
        application.setInterest(convertInterest(application.getInterest()));
    }

    private static int covertToMonths(int loanDurationYears) {
        return loanDurationYears * MONTHS_IN_YEAR;
    }

    private static double convertInterest(double interest) {
        return interest / 100.0 / MONTHS_IN_YEAR;
    }

    private static void validateApplication(LoanApplication application) {
        validateLoanAmount(application.getLoanAmount());
        validateLoanDuration(application.getLoanDuration());
        validateInterest(application.getInterest());
    }

    private static void validateLoanAmount(int loanAmount) {
        if (loanAmount < MIN_ALLOWED_INPUT) {
            throwExceptionForWrongInput("Loan amount");
        }
    }

    private static void validateLoanDuration(int loanDuration) {
        if (loanDuration < MIN_ALLOWED_INPUT) {
            throwExceptionForWrongInput("Loan duration");
        }
    }

    private static void validateInterest(double interest) {
        if (interest < MIN_ALLOWED_INPUT) {
            throwExceptionForWrongInput("Interest rate");
        }
    }

    private static void throwExceptionForWrongInput(String failedInputName) {
        throw new InvalidInputException(String.format("%s should be a positive number",failedInputName));
    }
}
