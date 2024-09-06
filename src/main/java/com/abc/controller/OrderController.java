package com.abc.controller;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.model.Order;
import com.abc.model.User;
import com.abc.service.OrderService;
import com.abc.service.UserService;
import com.abc.util.EmailUtility;
import com.abc.util.SalesReportUtility;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private OrderService orderService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        orderService = OrderService.getInstance();
        this.userService = UserService.getInstance();
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
        } else if (action.equals("downloadPdf")) {
            downloadPdf(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            processOrderFromCart(request, response);
        } else if (action.equals("add")) {
            addOrder(request, response);
        } else if (action.equals("update")) {
            updateOrder(request, response);
        }
    }
    
    private void processOrderFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String orderDetails = request.getParameter("orderDetails");
        String totalPrice = request.getParameter("totalPrice");

        String orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        Order order = new Order();
        order.setUserID(Integer.parseInt(userID));
        order.setOrderDetails(orderDetails);
        order.setOrderDate(orderDate);

        try {
            order.setTotalPrice(Double.parseDouble(totalPrice));
        } catch (NumberFormatException e) {
            order.setTotalPrice(0.0);
        }

        order.setStatus("pending");

        int orderID = orderService.addOrder(order);

        Order completeOrder = orderService.getOrderById(orderID);

        request.setAttribute("order", completeOrder);
        request.getRequestDispatcher("WEB-INF/view/orderSuccess.jsp").forward(request, response);
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
        
        // Retrieve the order by ID and update its status to 'accepted'
        Order order = orderService.getOrderById(orderID);
        if (order != null) {
            order.setStatus("accepted");
            orderService.updateOrder(order);
            
            // Fetch the customer details for the order
            User customer = userService.getUserById(order.getUserID());
            
            // Prepare the email content with your previous format
            String subject = "Order Confirmation - ABC Restaurant";
            String message = "Dear " + customer.getUsername() + ",\n\n" +
                             "Thank you for your order!\n\n" +
                             "Order Details\n" +
                             "Order ID: " + order.getOrderID() + "\n" +
                             "Order Date: " + order.getOrderDate() + "\n" +
                             "Order Details:\n" + order.getOrderDetails() + "\n\n" +
                             "Total Price: $" + order.getTotalPrice() + "\n\n" +
                             "We look forward to serving you again!\n\n" +
                             "Best regards,\n" +
                             "ABC Restaurant";
            
            // Send the confirmation email
            try {
                EmailUtility.sendEmail(customer.getEmail(), subject, message);
            } catch (Exception e) {
                e.printStackTrace(); // Log the error
            }
            
            // Redirect back to the list of orders
            response.sendRedirect("order?action=list");
        } else {
            // Handle case where the order is not found
            request.setAttribute("error", "Order not found.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }
    
    private void downloadPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=OrderReport.pdf");

            List<Order> orders = orderService.getAllOrders();

            SalesReportUtility.generateSalesReportPdf(orders, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating PDF");
        }
    }
}

