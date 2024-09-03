package com.abc.service;

import java.util.List;

import com.abc.dao.OrderDAO;
import com.abc.model.Order;

public class OrderService {
    private static OrderService instance;
    private OrderDAO orderDAO;

    private OrderService() {
        orderDAO = new OrderDAO();
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public int addOrder(Order order) {
        return orderDAO.addOrder(order);
    }

    public Order getOrderById(int orderID) {
        return orderDAO.getOrderById(orderID);
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
    
    public void acceptOrder(int orderID) {
        orderDAO.acceptOrder(orderID);
    }
}
