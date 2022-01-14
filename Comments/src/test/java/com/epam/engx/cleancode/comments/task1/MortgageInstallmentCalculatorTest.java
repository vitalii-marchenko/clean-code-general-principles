package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;
import org.junit.Assert;
import org.junit.Test;

public class MortgageInstallmentCalculatorTest {
    @Test
    public void shouldCalculateMonthlyPaymentWhenAmountsAreSmall() throws Exception {
        LoanApplication application = new LoanApplication(1000, 1, 12);

        double monthlyPaymentAmount = MortgageInstallmentCalculator.calculateMonthlyPayment(application);
        Assert.assertEquals(88.84d, monthlyPaymentAmount, 0.01d);
    }

    @Test
    public void shouldCalculateMonthlyPaymentWhenAmountIsLarge() throws Exception {
        LoanApplication application = new LoanApplication(10000000, 1, 12);

        double monthlyPaymentAmount = MortgageInstallmentCalculator.calculateMonthlyPayment(application);
        Assert.assertEquals(888487.88d, monthlyPaymentAmount, 0.01d);
    }

    @Test
    public void shouldCalculateMonthlyPaymentWhenPrincipalIsZero() throws Exception {
        LoanApplication application = new LoanApplication(0, 1, 12);

        double monthlyPaymentAmount = MortgageInstallmentCalculator.calculateMonthlyPayment(application);
        Assert.assertEquals(0, monthlyPaymentAmount, 0.01d);
    }

    @Test
    public void shouldCalculateMonthlyPaymentWhenInterestRateIsZero() throws Exception {
        LoanApplication application = new LoanApplication(1000, 1, 0);

        double monthlyPaymentAmount = MortgageInstallmentCalculator.calculateMonthlyPayment(application);
        Assert.assertEquals(83.33, monthlyPaymentAmount, 0.01d);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowInvalidInputExceptionOnNegativeTenure() throws Exception {
        LoanApplication application = new LoanApplication(20, -10, 14.5);

        MortgageInstallmentCalculator.calculateMonthlyPayment(application);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowInvalidInputExceptionOnNegativeInterestRate() throws Exception {
        LoanApplication application = new LoanApplication(20, 1, -12);

        MortgageInstallmentCalculator.calculateMonthlyPayment(application);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowInvalidInputExceptionOnNegativePrincipalAmount() throws Exception {
        LoanApplication application = new LoanApplication(-20, 10, 14.5);

        MortgageInstallmentCalculator.calculateMonthlyPayment(application);
    }
}
