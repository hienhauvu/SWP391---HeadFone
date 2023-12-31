/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Order;
import model.Product;
import model.Staff;

/**
 *
 * @author admin
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Product;

/**
 *
 * @author Acer Aspire
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Cart;

/**
 *
 * @author admin
 */
import jakarta.servlet.annotation.*;
@WebServlet(name = "StaffLoginController", urlPatterns = "/stafflogin")
public class StaffLoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Nhận thông tin
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        //Xử lí thông tin
        Staff s = new Staff(email, password);
        boolean check = s.checkLogin();

        //Trả kết quả về
        if (check) {
            //Lưu thông tin login vào session
            String role = s.getRole();
            Staff s1 = s.getStaff();
            HttpSession session = req.getSession();

            if(s1.getStatus()==1){
                req.setAttribute("mess", "Your account has been banned");
                req.getRequestDispatcher("Staff_login.jsp").forward(req, resp);
            }

            if (role.equals("Accountant")) {
                session.setAttribute("accountant", "accountant");
                req.getRequestDispatcher("accountantorder").forward(req, resp);
            } else if (role.equals("Marketing")) {
                Product p = new Product();
                List<Product> list = p.getAllProductReleaseDate();
                req.setAttribute("list", list);
                session.setAttribute("marketing", "marketing");
                req.getRequestDispatcher("Marketing.jsp").forward(req, resp);
            } else {
                Order o = new Order();
                List<Order> list = o.getListNotCompletedOrder(s1.getId());
                req.setAttribute("ord_id", s1.getId());
                req.setAttribute("list", list);
                req.getRequestDispatcher("OrderManager.jsp").forward(req, resp);
            }

        } else {
            req.setAttribute("email", email);
            req.setAttribute("password", password);
            req.setAttribute("mess", "Incorrect Email or Password");
            req.getRequestDispatcher("Staff_login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Staff_login.jsp").forward(req, resp);
    }

}
