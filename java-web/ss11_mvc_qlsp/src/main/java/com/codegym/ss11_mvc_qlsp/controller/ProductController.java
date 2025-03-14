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
                deleteById(req,resp);
                break;
            case "search":
                searchByName(req,resp);
                break;
            default:
                // trả về list
                showList(req,resp);

        }

    }

    private void searchByName(HttpServletRequest req, HttpServletResponse resp) {
        String searchName = req.getParameter("searchName");
        List<Product> searchList = productService.searchByName(searchName);
        req.setAttribute("productList", searchList);
        req.setAttribute("searchName", searchName);
        try {
            req.getRequestDispatcher("/views/product/list.jsp").forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int deleteId = Integer.parseInt(req.getParameter("deleteId"));
        boolean isDeleteSuccess =productService.deleteById(deleteId);
        String mess = "Delete not success";
        if(isDeleteSuccess){
            mess = "Delete success";
        }
        resp.sendRedirect("/products?mess="+mess);
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
        try {
            int id = Integer.parseInt(req.getParameter("id"));

            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String manufacturer = req.getParameter("manufacturer");

            double price = 0.0;
            if (req.getParameter("price") != null && !req.getParameter("price").trim().isEmpty()) {
                price = Double.parseDouble(req.getParameter("price"));
            }

            Product product = new Product(id, name, price, description, manufacturer);

            boolean flag = productService.add(product);

            if (flag) {
                resp.sendRedirect("/products?mess=Created success");
            } else {
                showFormCreate(req, resp);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Lỗi nhập liệu. Vui lòng kiểm tra lại.");
            showFormCreate(req, resp);
        }
    }

}