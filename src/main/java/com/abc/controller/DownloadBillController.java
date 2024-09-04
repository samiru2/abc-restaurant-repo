package com.abc.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.abc.service.OrderService;
import com.abc.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

@WebServlet("/downloadBill")
public class DownloadBillController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));

        OrderService orderService = OrderService.getInstance();
        Order order = orderService.getOrderById(orderID);

        if (order == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"order_" + orderID + ".pdf\"");

        try (OutputStream out = response.getOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            Paragraph title = new Paragraph("Order Confirmation", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.WHITE));
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(10);
            
            PdfPCell titleCell = new PdfPCell(title);
            titleCell.setBackgroundColor(new BaseColor(40, 167, 69));
            titleCell.setPadding(10);
            titleCell.setBorder(Rectangle.NO_BORDER);

            PdfPTable titleTable = new PdfPTable(1);
            titleTable.setWidthPercentage(100);
            titleTable.addCell(titleCell);
            document.add(titleTable);

            Paragraph thankYou = new Paragraph("Thank you " + order.getUsername() + ", for your order!", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            thankYou.setSpacingBefore(20);
            document.add(thankYou);

            Paragraph confirmation = new Paragraph("Your order has been placed successfully. Here are the details:", FontFactory.getFont(FontFactory.HELVETICA, 12));
            confirmation.setSpacingAfter(10);
            document.add(confirmation);

            Paragraph orderDetailsHeader = new Paragraph("Order Details", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            orderDetailsHeader.setSpacingBefore(10);
            document.add(orderDetailsHeader);

            List<String> orderDetails = Arrays.asList(
                "Order ID: " + order.getOrderID(),
                "Order Date: " + order.getOrderDate()
            );

            for (String detail : orderDetails) {
                document.add(new Paragraph(detail, FontFactory.getFont(FontFactory.HELVETICA, 12)));
            }

            Paragraph itemsHeader = new Paragraph("Items Ordered", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            itemsHeader.setSpacingBefore(20);
            document.add(itemsHeader);

            List<String> items = Arrays.asList(order.getOrderDetails().split(","));
            for (String item : items) {
                document.add(new Paragraph("• " + item, FontFactory.getFont(FontFactory.HELVETICA, 12)));
            }

            PdfPTable billSummaryTable = new PdfPTable(2);
            billSummaryTable.setWidthPercentage(100);
            billSummaryTable.setSpacingBefore(20);
            billSummaryTable.setWidths(new float[] { 3f, 1f });

            PdfPCell billSummaryHeader = new PdfPCell(new Phrase("Amount", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            billSummaryHeader.setBorder(Rectangle.NO_BORDER);
            billSummaryHeader.setPadding(5);
            billSummaryHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
            billSummaryHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell billSummaryPrice = new PdfPCell(new Phrase("Rs. " + order.getTotalPrice(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
            billSummaryPrice.setBorder(Rectangle.NO_BORDER);
            billSummaryPrice.setPadding(5);
            billSummaryPrice.setHorizontalAlignment(Element.ALIGN_RIGHT);
            billSummaryPrice.setVerticalAlignment(Element.ALIGN_MIDDLE);

            billSummaryTable.addCell(billSummaryHeader);
            billSummaryTable.addCell(billSummaryPrice);

            document.add(billSummaryTable);

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
