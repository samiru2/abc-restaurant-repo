package com.abc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.model.Facility;
import com.abc.model.Menu;
import com.abc.model.Offer;
import com.abc.model.Gallery;
import com.abc.model.Order;
import com.abc.service.FacilityService;
import com.abc.service.MenuService;
import com.abc.service.OfferService;
import com.abc.service.GalleryService;
import com.abc.service.OrderService;

@WebServlet("/index")
public class IndexController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private OfferService offerService;
    private MenuService menuService;
    private FacilityService facilityService;
    private GalleryService galleryService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        offerService = OfferService.getInstance();
        menuService = MenuService.getInstance();
        facilityService = FacilityService.getInstance();
        galleryService = GalleryService.getInstance();
        orderService = OrderService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Offer> offers = offerService.getAllOffers();
            request.setAttribute("offers", offers);
            
            List<Menu> menus = menuService.getAllMenus();
            request.setAttribute("menus", menus);

            List<Facility> facilities = facilityService.getAllFacilities();
            request.setAttribute("facilities", facilities);
            
            List<Gallery> galleries = galleryService.getAllGalleries();
            request.setAttribute("galleries", galleries); 

            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unable to fetch offers, menus, facilities, and gallery items.");
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}
