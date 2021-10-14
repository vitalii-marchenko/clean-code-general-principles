package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.exceptions.UserDaoNotInitializedException;
import com.epam.engx.cleancode.errorhandling.task1.exceptions.UserNotFoundException;
import com.epam.engx.cleancode.errorhandling.task1.exceptions.UserNotHaveSubmittedOrdersException;
import com.epam.engx.cleancode.errorhandling.task1.exceptions.WrongOrderAmountException;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Order;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.User;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.UserDao;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserReportBuilder {

    private UserDao userDao;

    public Double getUserTotalOrderAmount(String userId) {
        User user = getUserById(userId);
        List<Order> orders = getAllValidOrdersOf(user);

        return countTotalOrderAmount(orders);
    }

    private double countTotalOrderAmount(List<Order> orders) {
        return orders.stream()
                .map(Order::total)
                .reduce(0.0, Double::sum);
    }

    private List<Order> getAllValidOrdersOf(User user) {
        List<Order> orders = user.getAllOrders();
        validateOrdersPresent(orders);
        validateOrdersPrice(getSubmittedOrders(orders));
        return getSubmittedOrders(orders);
    }

    private void validateOrdersPrice(List<Order> orders) {
        for (Order order : orders) {
            Double total = order.total();
            if (total < 0) {
                throw new WrongOrderAmountException(String.format("Wrong order: %s amount: %s", order, total));
            }
        }
    }

    private List<Order> getSubmittedOrders(List<Order> orders) {
        return orders.stream()
                .filter(Order::isSubmitted)
                .collect(toList());
    }

    private void validateOrdersPresent(List<Order> orders) {
        if (orders.isEmpty()) {
            throw new UserNotHaveSubmittedOrdersException("User have no submitted orders.");
        }
    }


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private User getUserById(String id) {
        User user = getUserDao().getUser(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("User with ID: %s doesn't exist.", id));
        }
        return user;
    }

    private UserDao getUserDao() {
        if (userDao == null) {
            throw new UserDaoNotInitializedException();
        }
        return userDao;
    }
}
