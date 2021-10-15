package com.epam.engx.cleancode.comments.task1;

public class LoanApplication {
    private int loanAmount;
    private int loanDurationYears;
    private double interestPercent;
    private int loanDurationMoths;
    private double mothlyInterestDecimal;

    public LoanApplication(int loanAmount, int loanDurationYears, double interestPercent) {
        this.loanAmount = loanAmount;
        this.loanDurationYears = loanDurationYears;
        this.interestPercent = interestPercent;
        this.loanDurationMoths = 0;
        this.mothlyInterestDecimal = 0.0;
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

    public double getMothlyInterestDecimal() {
        return mothlyInterestDecimal;
    }

    public void setMothlyInterestDecimal(double mothlyInterestDecimal) {
        this.mothlyInterestDecimal = mothlyInterestDecimal;
    }
}
