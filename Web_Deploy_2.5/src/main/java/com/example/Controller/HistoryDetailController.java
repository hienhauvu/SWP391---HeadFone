/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Order;
import model.OrderDetail;

/**
 *
 * @author admin
 */
@WebServlet(name = "HistoryDetailController", urlPatterns = "/historydetail")
public class HistoryDetailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int oid = Integer.parseInt(req.getParameter("oid"));
        OrderDetail od = new OrderDetail();
        List<OrderDetail> historyDetails = od.getHistory(oid);
        float t = od.getTotalMoney(historyDetails);
        Order o=new Order();
        String address=o.getOrderAddress(oid);
        String phone=o.getOrderPhone(oid);
        String date=o.getOrderDate(oid);
        
        req.setAttribute("address", address);
        req.setAttribute("phone", phone);
        req.setAttribute("date", date);
        req.setAttribute("total", t);
        req.setAttribute("oid", oid);
        req.setAttribute("historyDetails", historyDetails);
        req.getRequestDispatcher("HistoryDetail.jsp").forward(req, resp);
    }

}
