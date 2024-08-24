package com.abc.service;

import java.util.List;
import com.abc.dao.OrderDAO;
import com.abc.model.Order;

public class OrderService {

    private static OrderService instance;
    private OrderDAO orderDAO;

    private OrderService() {
        this.orderDAO = new OrderDAO();
    }

    public static OrderService getInstance() {
        if (instance == null) {
            synchronized (OrderService.class) {
                if (instance == null) {
                    instance = new OrderService();
                }
            }
        }
        return instance;
    }

    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }

    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    public void deleteOrder(int orderID) {
        orderDAO.deleteOrder(orderID);
    }

    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    public Order getOrderById(int orderID) {
        return orderDAO.getOrderById(orderID);
    }
}
