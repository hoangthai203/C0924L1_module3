package com.codegym.demo_bai_thi.controller;

import com.codegym.demo_bai_thi.model.Product;
import com.codegym.demo_bai_thi.service.IProductService;
import com.codegym.demo_bai_thi.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductController extends HttpServlet {
    private IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showFormCreate(req, resp);
                break;
            case "delete":
                deleteById(req, resp);
                break;
            case "search":
                searchProducts(req, resp);
                break;
            default:
                showList(req, resp);
        }
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productId = req.getParameter("deleteId");
        boolean isDeleteSuccess = productService.deleteById(productId);
        String mess = isDeleteSuccess ? "Delete success" : "Delete not success";
        resp.sendRedirect("/products?mess=" + mess);
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> unitList = productService.getAllUnits();
        List<String> categoryList = productService.getAllCategories();
        req.setAttribute("unitList", unitList);
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("/views/product/form.jsp").forward(req, resp);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = productService.findAll();
        List<String> categoryList = productService.getAllCategories();
        req.setAttribute("productList", productList);
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("/views/product/list.jsp").forward(req, resp);
    }

    private void searchProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String category = req.getParameter("category");
        List<Product> searchResults = productService.searchProducts(name, category);
        List<String> categoryList = productService.getAllCategories();
        req.setAttribute("productList", searchResults);
        req.setAttribute("categoryList", categoryList);
        req.setAttribute("searchName", name);
        req.setAttribute("searchCategory", category);
        req.getRequestDispatcher("/views/product/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                save(req, resp);
                break;
            case "delete":
                deleteById(req, resp);
                break;
            default:
        }
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String productId = req.getParameter("productId");
            String name = req.getParameter("name");
            String unit = req.getParameter("unit");
            String category = req.getParameter("category");
            double price = 0.0;

            if (req.getParameter("price") != null && !req.getParameter("price").trim().isEmpty()) {
                price = Double.parseDouble(req.getParameter("price"));
            }

            // Validate productId format (MHH-XXXX)
            if (!productId.matches("MHH-[A-Z0-9]{4}")) {
                req.setAttribute("error", "Mã hàng hóa phải có định dạng MHH-XXXX, với X là số hoặc chữ in HOA");
                showFormCreate(req, resp);
                return;
            }

            // Validate price
            if (price < 1000) {
                req.setAttribute("error", "Giá phải là số nguyên dương và >= 1.000 VNĐ");
                showFormCreate(req, resp);
                return;
            }

            Product product = new Product(productId, name, unit, price, category);
            boolean flag = productService.add(product);

            if (flag) {
                resp.sendRedirect("/products?mess=Created success");
            } else {
                req.setAttribute("error", "Thêm sản phẩm thất bại!");
                showFormCreate(req, resp);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Lỗi nhập liệu. Vui lòng kiểm tra lại.");
            showFormCreate(req, resp);
        }
    }
}
