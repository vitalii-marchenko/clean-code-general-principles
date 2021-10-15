package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.exceptions.UserDaoNotInitializedException;
import com.epam.engx.cleancode.errorhandling.task1.exceptions.report.UserNotFoundException;
import com.epam.engx.cleancode.errorhandling.task1.exceptions.report.UserNotHaveSubmittedOrdersException;
import com.epam.engx.cleancode.errorhandling.task1.exceptions.report.WrongOrderAmountException;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Order;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.User;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.UserDao;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserReportBuilder {

    private static final String USER_WITH_ID_NOT_EXIST_WARNING = "WARNING: User ID doesn't exist.";
    private static final String USER_NOT_HAVE_SUBMITTED_ORDERS_WARNING = "WARNING: User have no submitted orders.";
    private static final String WRONG_ORDER_AMOUNT_ERROR = "ERROR: Wrong order amount.";
    private static final String TECHNICAL_ERROR = "technicalError";
    private static final String CURRENCY_SYMBOL = "$";
    private static final String USER_TOTAL_MESSAGE_GENERAL_PART = "User Total: ";

    private UserDao userDao;

    public Double getUserTotalOrderAmount(String userId) {
        User user = getUserById(userId);
        List<Order> orders = getAllValidOrdersOf(user);

        return countTotalOrderAmount(orders);
    }

    protected static String buildUserTotalMessage(double amount) {
        return USER_TOTAL_MESSAGE_GENERAL_PART + amount + CURRENCY_SYMBOL;
    }

    private double countTotalOrderAmount(List<Order> orders) {
        return orders.stream()
                .map(Order::total)
                .reduce(0.0, Double::sum);
    }

    private List<Order> getAllValidOrdersOf(User user) {
        List<Order> orders = user.getAllOrders();
        validateOrders(orders);
        return getSubmittedOrders(orders);
    }

    private void validateOrders(List<Order> orders) {
        validateOrdersPresent(orders);
        validateOrdersPrice(getSubmittedOrders(orders));
    }

    private void validateOrdersPrice(List<Order> orders) {
        for (Order order : orders) {
            Double total = order.total();
            validateTotalPrice(total);
        }
    }

    private void validateTotalPrice(Double total) {
        if (total < 0) {
            throw new WrongOrderAmountException(WRONG_ORDER_AMOUNT_ERROR);
        }
    }

    private List<Order> getSubmittedOrders(List<Order> orders) {
        return orders.stream()
                .filter(Order::isSubmitted)
                .collect(toList());
    }

    private void validateOrdersPresent(List<Order> orders) {
        if (orders.isEmpty()) {
            throw new UserNotHaveSubmittedOrdersException(USER_NOT_HAVE_SUBMITTED_ORDERS_WARNING);
        }
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private User getUserById(String id) {
        User user = getUserDao().getUser(id);
        if (user == null) {
            throw new UserNotFoundException(USER_WITH_ID_NOT_EXIST_WARNING);
        }
        return user;
    }

    private UserDao getUserDao() {
        if (userDao == null) {
            throw new UserDaoNotInitializedException(TECHNICAL_ERROR);
        }
        return userDao;
    }
}
