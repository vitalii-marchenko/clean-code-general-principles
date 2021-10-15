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
    private static final int ONE_DAY = 1;
    private static final BigDecimal NO_INTEREST = BigDecimal.ZERO;


    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        if (isAccountStartedAfterBonusAge(accountDetails)) {
            return countInterest(accountDetails);
        } else {
            return NO_INTEREST;
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

        return countElapsedYears(startCalendar, endCalendar);
    }

    private int durationSinceDateInYears(Date startDate) {
        return durationBetweenDatesInYears(startDate, new Date());
    }

    private boolean isNotDayReached(Calendar startCalendar, Calendar endCalendar) {
        return endCalendar.get(Calendar.DAY_OF_YEAR) + LEAP_YEAR_SHIFT < startCalendar.get(Calendar.DAY_OF_YEAR);
    }

    private int countElapsedYears(Calendar start, Calendar end) {
        int toYear = end.get(Calendar.YEAR);
        int fromYear = start.get(Calendar.YEAR);
        int elapsedYears = toYear - fromYear;

        if (isNotDayReached(start, end)) {
            return elapsedYears - ONE_DAY;
        }
        return elapsedYears;
    }

    private BigDecimal countInterest(AccountDetails accountDetails) {
        double interest = calculateInterestForAccount(accountDetails);
        return BigDecimal.valueOf(interest);
    }

    private double calculateInterestForAccount(AccountDetails accountDetails) {
        double appliedPercent = getAppliedPercentByAge(durationSinceDateInYears(accountDetails.getBirth()));
        return accountDetails.getBalance().doubleValue() * durationSinceDateInYears(accountDetails.getStartDate()) * appliedPercent / 100;
    }

    private double getAppliedPercentByAge(int applicantAge) {
        if (AGE <= applicantAge) {
            return SENIOR_PERCENT;
        }
        return INTEREST_PERCENT;
    }
}
