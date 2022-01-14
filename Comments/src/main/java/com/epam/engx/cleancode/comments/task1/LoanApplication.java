package com.epam.engx.cleancode.comments.task1;

public class LoanApplication {
    private static final int MONTHS_IN_YEAR = 12;

    private int loanAmount;
    private int loanDurationYears;
    private double interestPercent;
    private int loanDurationMoths;
    private double monthlyInterestDecimal;

    public LoanApplication(int loanAmount, int loanDurationYears, double interestPercent) {
        this.loanAmount = loanAmount;
        this.loanDurationYears = loanDurationYears;
        this.interestPercent = interestPercent;
        this.loanDurationMoths = covertToMonths(getLoanDurationYears());
        this.monthlyInterestDecimal = convertToMonthlyInterestDecimal(getInterestPercent());
    }

    private int covertToMonths(int loanDurationYears) {
        return loanDurationYears * MONTHS_IN_YEAR;
    }

    private double convertToMonthlyInterestDecimal(double interest) {
        return interest / 100.0 / MONTHS_IN_YEAR;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public int getLoanDurationYears() {
        return loanDurationYears;
    }

    public double getInterestPercent() {
        return interestPercent;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setLoanDurationYears(int loanDurationYears) {
        this.loanDurationYears = loanDurationYears;
    }

    public void setInterestPercent(double interestPercent) {
        this.interestPercent = interestPercent;
    }

    public int getLoanDurationMoths() {
        return loanDurationMoths;
    }

    public void setLoanDurationMoths(int loanDurationMoths) {
        this.loanDurationMoths = loanDurationMoths;
    }

    public double getMonthlyInterestDecimal() {
        return monthlyInterestDecimal;
    }

    public void setMonthlyInterestDecimal(double monthlyInterestDecimal) {
        this.monthlyInterestDecimal = monthlyInterestDecimal;
    }
}
