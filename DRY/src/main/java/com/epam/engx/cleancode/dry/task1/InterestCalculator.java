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

        return countDiffYears(startCalendar, endCalendar);
    }

    private int durationSinceStartDateInYears(Date startDate) {
        return durationBetweenDatesInYears(startDate, new Date());
    }

    private boolean isNotDayReached(Calendar startCalendar, Calendar endCalendar) {
        return endCalendar.get(Calendar.DAY_OF_YEAR) + LEAP_YEAR_SHIFT < startCalendar.get(Calendar.DAY_OF_YEAR);
    }

    private int countDiffYears(Calendar start, Calendar end) {
        int to = end.get(Calendar.YEAR);
        int from = start.get(Calendar.YEAR);
        int diff = to - from;

        if (isNotDayReached(start, end)) {
            return diff - ONE_DAY;
        }
        return diff;
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
}
