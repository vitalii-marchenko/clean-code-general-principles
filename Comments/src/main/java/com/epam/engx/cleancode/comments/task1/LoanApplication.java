package com.epam.engx.cleancode.comments.task1;

public class LoanApplication {
    private int loanAmount;
    private int loanDuration;
    private double interest;

    public LoanApplication(int loanAmount, int loanDuration, double interest) {
        this.loanAmount = loanAmount;
        this.loanDuration = loanDuration;
        this.interest = interest;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public int getLoanDuration() {
        return loanDuration;
    }

    public double getInterest() {
        return interest;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setLoanDuration(int loanDuration) {
        this.loanDuration = loanDuration;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
}
