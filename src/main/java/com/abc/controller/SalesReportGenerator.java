package com.abc.controller;

import com.abc.model.Order;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.encoders.EncoderUtil;
import org.jfree.chart.encoders.ImageFormat;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class SalesReportGenerator {

    public static void generateOrderReportPdf(List<Order> orders, OutputStream outputStream) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        document.add(new Paragraph("Order Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));

        addSummary(document, orders);

        addOrdersTable(document, orders, "Pending Orders", BaseColor.RED, "pending");

        addOrdersTable(document, orders, "Accepted Orders", BaseColor.GREEN, "accepted");

        addRevenueChart(document, orders);

        document.close();
    }

    private static void addSummary(Document document, List<Order> orders) throws DocumentException {
        int pendingCount = 0;
        int acceptedCount = 0;
        double totalRevenue = 0.0;

        for (Order order : orders) {
            if ("pending".equals(order.getStatus())) {
                pendingCount++;
            } else if ("accepted".equals(order.getStatus())) {
                acceptedCount++;
                totalRevenue += order.getTotalPrice();
            }
        }

        document.add(new Paragraph("Summary", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
        document.add(new Paragraph("Total Pending Orders: " + pendingCount, FontFactory.getFont(FontFactory.HELVETICA, 12)));
        document.add(new Paragraph("Total Accepted Orders: " + acceptedCount, FontFactory.getFont(FontFactory.HELVETICA, 12)));
        document.add(new Paragraph("Total Revenue: $" + String.format("%.2f", totalRevenue), FontFactory.getFont(FontFactory.HELVETICA, 12)));
        document.add(Chunk.NEWLINE);
    }

    private static void addOrdersTable(Document document, List<Order> orders, String title, BaseColor headerColor, String statusFilter) throws DocumentException {
        // Add section title
        Paragraph sectionTitle = new Paragraph(title, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
        sectionTitle.setSpacingBefore(20);
        sectionTitle.setSpacingAfter(10);
        document.add(sectionTitle);

        PdfPTable table = new PdfPTable(9);

        PdfPCell headerCell;

        String[] headers = {"Order ID", "User ID", "Order Date", "Username", "Phone", "Email", "Order Details", "Total Price", "Status"};

        for (String header : headers) {
            headerCell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            headerCell.setBackgroundColor(headerColor);
            table.addCell(headerCell);
        }

        for (Order order : orders) {
            if (order.getStatus().equals(statusFilter)) {
                table.addCell(String.valueOf(order.getOrderID()));
                table.addCell(String.valueOf(order.getUserID()));
                table.addCell(order.getOrderDate());
                table.addCell(order.getUsername());
                table.addCell(order.getPhone());
                table.addCell(order.getEmail());
                table.addCell(order.getOrderDetails());
                table.addCell(String.valueOf(order.getTotalPrice()));
                table.addCell(order.getStatus());
            }
        }

        document.add(table);
    }

    private static void addRevenueChart(Document document, List<Order> orders) throws DocumentException, IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Double> revenueMap = new HashMap<>();

        for (Order order : orders) {
            if ("accepted".equals(order.getStatus())) {
                String orderDate = order.getOrderDate();
                revenueMap.put(orderDate, revenueMap.getOrDefault(orderDate, 0.0) + order.getTotalPrice());
            }
        }

        for (Map.Entry<String, Double> entry : revenueMap.entrySet()) {
            dataset.addValue(entry.getValue(), "Revenue", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Revenue per Day",       
                "Date",                  
                "Revenue ($)",           
                dataset,                 
                PlotOrientation.VERTICAL, 
                false,                   
                true,                    
                false                   
        );

        int width = 500;
        int height = 300;
        BufferedImage bufferedImage = chart.createBufferedImage(width, height);
        byte[] imageBytes = EncoderUtil.encode(bufferedImage, ImageFormat.PNG, true);

        Image chartImage = Image.getInstance(imageBytes);
        chartImage.setAlignment(Element.ALIGN_CENTER);
        chartImage.setSpacingBefore(20);
        chartImage.setSpacingAfter(20);

        document.add(chartImage);
    }
}
