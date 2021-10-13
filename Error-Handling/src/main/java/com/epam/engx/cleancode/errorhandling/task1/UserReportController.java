package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.exceptions.UserDaoNotInitializedException;
import com.epam.engx.cleancode.errorhandling.task1.exceptions.UserNotFoundException;
import com.epam.engx.cleancode.errorhandling.task1.exceptions.UserNotHaveSubmittedOrdersException;
import com.epam.engx.cleancode.errorhandling.task1.exceptions.WrongOrderAmountException;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Model;

public class UserReportController {

    private static final String USER_WITH_ID_NOT_EXIST_WARNING = "WARNING: User ID doesn't exist.";
    private static final String USER_NOT_HAVE_SUBMITTED_ORDERS_WARNING = "WARNING: User have no submitted orders.";
    private static final String WRONG_ORDER_AMOUNT_ERROR = "ERROR: Wrong order amount.";
    private static final String TECHNICAL_ERROR = "technicalError";
    private static final String CURRENCY_SYMBOL = "$";
    private static final String USER_TOTAL_MESSAGE_GENERAL_PART = "User Total: ";

    private UserReportBuilder userReportBuilder;

    public String getUserTotalOrderAmountView(String userId, Model model) {
        try {
            String totalMessage = getUserTotalMessage(userId);
            model.addAttribute("userTotalMessage", totalMessage);
            return "userTotal";
        } catch (UserDaoNotInitializedException ex) {
            return TECHNICAL_ERROR;
        }
    }

    private String getUserTotalMessage(String userId) {
        try {
            double amount = userReportBuilder.getUserTotalOrderAmount(userId);
            return buildUserTotalMessage(amount);
        } catch (UserNotFoundException ex) {
            return USER_WITH_ID_NOT_EXIST_WARNING;
        } catch (UserNotHaveSubmittedOrdersException ex) {
            return USER_NOT_HAVE_SUBMITTED_ORDERS_WARNING;
        } catch (WrongOrderAmountException ex) {
            return WRONG_ORDER_AMOUNT_ERROR;
        }
    }

    private String buildUserTotalMessage(double amount) {
        return USER_TOTAL_MESSAGE_GENERAL_PART + amount + CURRENCY_SYMBOL;
    }

    public UserReportBuilder getUserReportBuilder() {
        return userReportBuilder;
    }

    public void setUserReportBuilder(UserReportBuilder userReportBuilder) {
        this.userReportBuilder = userReportBuilder;
    }
}
