package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {

    private static final int MIN_ALLOWED_INPUT = 0;

    public static double calculateMonthlyPayment(LoanApplication application) {
        validateApplication(application);
        return calculatePayment(application);
    }

    private static double calculatePayment(LoanApplication application) {
        if (isZeroInterest(application.getInterestPercent())) {
            return calculateMonthlyPaymentWithZeroInterest(application);
        }
        return calculateMonthlyPaymentWithNonZeroInterest(application);
    }

    private static double calculateMonthlyPaymentWithZeroInterest(LoanApplication application) {
        return (double) application.getLoanAmount() / application.getLoanDurationMoths();
    }

    private static double calculateMonthlyPaymentWithNonZeroInterest(LoanApplication application) {
        double monthlyInterest = application.getMonthlyInterestDecimal();
        return getPercentPartInMonthlyPayment(application.getLoanAmount(), monthlyInterest) /
                (1 - Math.pow(1 + monthlyInterest, (-1) * application.getLoanDurationMoths()));
    }

    private static double getPercentPartInMonthlyPayment(int loanAmount, double monthlyInterest) {
        return loanAmount * monthlyInterest;
    }

    private static boolean isZeroInterest(double interest) {
        return interest == MIN_ALLOWED_INPUT;
    }

    private static void validateApplication(LoanApplication application) {
        validateLoanAmount(application.getLoanAmount());
        validateLoanDuration(application.getLoanDurationYears());
        validateInterest(application.getInterestPercent());
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
