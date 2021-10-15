package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.exceptions.*;
import com.epam.engx.cleancode.errorhandling.task1.exceptions.report.UserReportException;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Model;

import static com.epam.engx.cleancode.errorhandling.task1.UserReportBuilder.*;

public class UserReportController {

    private UserReportBuilder userReportBuilder;

    public String getUserTotalOrderAmountView(String userId, Model model) {
        try {
            String totalMessage = getUserTotalMessage(userId);
            model.addAttribute("userTotalMessage", totalMessage);
            return "userTotal";
        } catch (UserDaoNotInitializedException ex) {
            return ex.getMessage();
        }
    }

    private String getUserTotalMessage(String userId) {
        try {
            double amount = userReportBuilder.getUserTotalOrderAmount(userId);
            return buildUserTotalMessage(amount);
        } catch (UserReportException ex) {
            return ex.getMessage();
        }
    }

    public UserReportBuilder getUserReportBuilder() {
        return userReportBuilder;
    }

    public void setUserReportBuilder(UserReportBuilder userReportBuilder) {
        this.userReportBuilder = userReportBuilder;
    }
}
