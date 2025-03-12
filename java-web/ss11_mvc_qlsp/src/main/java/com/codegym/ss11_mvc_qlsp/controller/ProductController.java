package com.codegym.ss11_mvc_qlsp.controller;

import com.codegym.ss11_mvc_qlsp.model.Product;
import com.codegym.ss11_mvc_qlsp.service.IProductService;
import com.codegym.ss11_mvc_qlsp.service.ProductService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet",value = "/products")
public class ProductController extends HttpServlet {
    private IProductService productService = new ProductService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action ==null){
            action = "";
        }
        switch (action){
            case "create":
                // trả về form thê mới
                showFormCreate(req,resp);
                break;
            case "delete":
                // xoá
                break;
            case "update":
                // update
                break;
            default:
                // trả về list
                showList(req,resp);

        }

    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/views/product/form.jsp").forward(req,resp);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = productService.findAll();
        req.setAttribute("productList", productList);
        req.getRequestDispatcher("/views/product/list.jsp").forward(req,resp);
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action ==null){
            action = "";
        }
        switch (action){
            case "create":
                // trả về form thê mới
                // gọi xuống service để thê mới
                save(req,resp);
                break;
            case "delete":
                // xoá
                break;
            case "update":
                // update
                break;
            default:

        }
    }
    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double pirce = Double.parseDouble(req.getParameter("pirce"));
        String description = req.getParameter("description");
        String manufacturer = req.getParameter("manufacturer");
        Product product = new Product(id,name,pirce,description,manufacturer);
        boolean flag = productService.add(product);
        if (flag){
            // thêm mới thành công
            resp.sendRedirect("/students?mess=Created succes");
        }else {
            // không thaành công trả vè form thêm mới
            showFormCreate(req,resp);
        }

    }
}