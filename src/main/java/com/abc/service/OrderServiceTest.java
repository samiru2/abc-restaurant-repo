package com.abc.service;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.abc.model.Order;
import java.util.List;

public class OrderServiceTest {
    private OrderService orderService;

    @Before
    public void setUp() {
        orderService = OrderService.getInstance();
    }

    @Test
    public void testAddOrder() {
        Order order = new Order(1, "Test order details", "2024-09-10 10:00:00", 50.0, "pending");
        int orderID = orderService.addOrder(order);
        assertTrue("Order ID should be greater than 0", orderID > 0);
        
        Order addedOrder = orderService.getOrderById(orderID);
        assertNotNull("Order should not be null", addedOrder);
        assertEquals("Order details should match", order.getOrderDetails(), addedOrder.getOrderDetails());
        assertEquals("Total price should match", order.getTotalPrice(), addedOrder.getTotalPrice(), 0.01);
    }

    @Test
    public void testUpdateOrder() {
        Order order = new Order(1, "Test order details", "2024-09-10 10:00:00", 50.0, "pending");
        int orderID = orderService.addOrder(order);
        order.setOrderID(orderID);
        order.setStatus("accepted");

        orderService.updateOrder(order);

        Order updatedOrder = orderService.getOrderById(orderID);
        assertNotNull("Order should not be null", updatedOrder);
        assertEquals("Order status should be updated to 'accepted'", "accepted", updatedOrder.getStatus());
    }

    @Test
    public void testDeleteOrder() {
        Order order = new Order(1, "Test order details", "2024-09-10 10:00:00", 50.0, "pending");
        int orderID = orderService.addOrder(order);
        orderService.deleteOrder(orderID);

        Order deletedOrder = orderService.getOrderById(orderID);
        assertNull("Order should be null after deletion", deletedOrder);
    }

    @Test
    public void testGetAllOrders() {
        Order order1 = new Order(1, "Order details 1", "2024-09-10 10:00:00", 30.0, "pending");
        Order order2 = new Order(1, "Order details 2", "2024-09-10 10:30:00", 40.0, "pending");
        orderService.addOrder(order1);
        orderService.addOrder(order2);

        List<Order> orders = orderService.getAllOrders();
        assertFalse("Order list should not be empty", orders.isEmpty());
        assertTrue("Order list should contain at least 2 orders", orders.size() >= 2);
    }
}
