package com.epam.engx.cleancode.dry.task1;

import com.epam.engx.cleancode.dry.task1.thirdpartyjar.Profitable;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InterestCalculator implements Profitable {

    private static final int AGE = 60;
    private static final double INTEREST_PERCENT = 4.5d;
    private static final double SENIOR_PERCENT = 5.5d;
    private static final int BONUS_AGE = 13;
    private static final int LEAP_YEAR_SHIFT = 1;


    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        if (isAccountStartedAfterBonusAge(accountDetails)) {
            return interest(accountDetails);
        } else {
            return BigDecimal.ZERO;
        }
    }

    private boolean isAccountStartedAfterBonusAge(AccountDetails accountDetails) {
        return durationBetweenDatesInYears(accountDetails.getBirth(), accountDetails.getStartDate()) > BONUS_AGE;
    }

    private int durationBetweenDatesInYears(Date from, Date to) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(from);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(to);

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        if (endCalendar.get(Calendar.DAY_OF_YEAR) + LEAP_YEAR_SHIFT < startCalendar.get(Calendar.DAY_OF_YEAR)) {
            return diffYear - 1;
        }
        return diffYear;
    }

    private BigDecimal interest(AccountDetails accountDetails) {
        double interest = calculateInterestDependsFromAge(accountDetails);
        return BigDecimal.valueOf(interest);
    }

    private double calculateInterestDependsFromAge(AccountDetails accountDetails) {
        double appliedPercent = getAppliedPercentByAge(accountDetails.getAge());
        return accountDetails.getBalance().doubleValue() * durationSinceStartDateInYears(accountDetails.getStartDate()) * appliedPercent / 100;
    }

    private double getAppliedPercentByAge(int applicantAge) {
        if (AGE <= applicantAge) {
            return SENIOR_PERCENT;
        }
        return INTEREST_PERCENT;
    }


    private int durationSinceStartDateInYears(Date startDate) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(startDate);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(new Date());

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        if (endCalendar.get(Calendar.DAY_OF_YEAR) + LEAP_YEAR_SHIFT < startCalendar.get(Calendar.DAY_OF_YEAR))
            return diffYear - 1;
        return diffYear;

    }
}
