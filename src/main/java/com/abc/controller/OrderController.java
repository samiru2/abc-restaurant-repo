package com.abc.controller;

import java.io.IOException;
import java.util.ArrayList;
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
        List<Order> orders = new ArrayList<>();
        try {
            orders = orderService.getAllOrders();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("WEB-INF/view/listOrders.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/addOrder.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("id"));
        Order existingOrder = orderService.getOrderById(orderID);
        request.setAttribute("order", existingOrder);
        request.getRequestDispatcher("WEB-INF/view/editOrder.jsp").forward(request, response);
    }

    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        int menuID = Integer.parseInt(request.getParameter("menuID"));
        String type = request.getParameter("type");
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        String status = request.getParameter("status");

        Order newOrder = new Order(userID, menuID, type, totalPrice, status);
        orderService.addOrder(newOrder);
        response.sendRedirect("order?action=list");
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int userID = Integer.parseInt(request.getParameter("userID"));
        int menuID = Integer.parseInt(request.getParameter("menuID"));
        String type = request.getParameter("type");
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        String status = request.getParameter("status");

        Order order = new Order(orderID, userID, menuID, type, totalPrice, status);
        orderService.updateOrder(order);
        response.sendRedirect("order?action=list");
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("id"));
        orderService.deleteOrder(orderID);
        response.sendRedirect("order?action=list");
    }
}

