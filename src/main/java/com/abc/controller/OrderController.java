package com.abc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.model.Order;
import com.abc.service.OrderService;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = OrderService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.equals("list")) {
            listOrders(request, response);
        } else if (action.equals("add")) {
            showAddForm(request, response);
        } else if (action.equals("edit")) {
            showEditForm(request, response);
        } else if (action.equals("delete")) {
            deleteOrder(request, response);
        } else if (action.equals("accept")) {
            acceptOrder(request, response); 
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            addOrder(request, response);
        } else if (action.equals("update")) {
            updateOrder(request, response);
        }
    }

    private void listOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderService.getAllOrders();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("WEB-INF/view/listOrders.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/addOrder.jsp").forward(request, response);
    }

    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        String orderDetails = request.getParameter("orderDetails");
        String orderDate = request.getParameter("orderDate");
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        String status = request.getParameter("status");

        Order newOrder = new Order(userID, orderDetails, orderDate, totalPrice, status);
        orderService.addOrder(newOrder);

        response.sendRedirect("order?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        Order existingOrder = orderService.getOrderById(orderID);
        request.setAttribute("order", existingOrder);
        request.getRequestDispatcher("WEB-INF/view/editOrder.jsp").forward(request, response);
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int userID = Integer.parseInt(request.getParameter("userID"));
        String orderDetails = request.getParameter("orderDetails");
        String orderDate = request.getParameter("orderDate");
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        String status = request.getParameter("status");

        Order updatedOrder = new Order(orderID, userID, orderDetails, orderDate, totalPrice, status, null, null, null);
        orderService.updateOrder(updatedOrder);

        response.sendRedirect("order?action=list");
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        orderService.deleteOrder(orderID);
        response.sendRedirect("order?action=list");
    }
    
    private void acceptOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        orderService.acceptOrder(orderID);
        response.sendRedirect("order?action=list");
    }
}

