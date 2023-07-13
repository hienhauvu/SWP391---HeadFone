package com.example.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Category;
import model.Product;

import jakarta.servlet.annotation.*;

@WebServlet(name = "HomeController", urlPatterns = {"/home","/Home",""})
public class HomeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product p = new Product();
        Category c = new Category();
        List<Category> list = c.getAllCategory();
        List<Product> hlist = p.getAllProductsByCategory2(1);
        List<Product> elist = p.getAllProductsByCategory2(2);
        List<Product> hslist = p.getAllProductsByCategory2(3);
        List<Product> alist = p.getAllProductsByCategory2(4);
        List<Product> tlist = p.getTrendingProduct();
        req.setAttribute("categoryList", list);
        req.setAttribute("earphoneList", elist);
        req.setAttribute("headphoneList", hlist);
        req.setAttribute("headsetList", hslist);
        req.setAttribute("airpodList", alist);
        req.setAttribute("trendingList", tlist);
        req.getRequestDispatcher("Home.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product p = new Product();
        Category c = new Category();
        List<Category> list = c.getAllCategory();
        List<Product> hlist = p.getAllProductsByCategory2(1);
        List<Product> elist = p.getAllProductsByCategory2(2);
        List<Product> hslist = p.getAllProductsByCategory2(3);
        List<Product> alist = p.getAllProductsByCategory2(4);
        List<Product> tlist = p.getTrendingProduct();
        req.setAttribute("categoryList", list);
        req.setAttribute("earphoneList", elist);
        req.setAttribute("headphoneList", hlist);
        req.setAttribute("headsetList", hslist);
        req.setAttribute("airpodList", alist);
        req.setAttribute("trendingList", tlist);
        req.getRequestDispatcher("Home.jsp").forward(req, resp);
    }
}