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
                searchByName(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            default:
                showList(req, resp);
        }

    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int deleteId = Integer.parseInt(req.getParameter("deleteId"));
        boolean isDeleteSuccess = productService.deleteById(deleteId);
        String mess = isDeleteSuccess ? "Delete success" : "Delete not success";
        resp.sendRedirect("/products?mess=" + mess);
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/product/form.jsp").forward(req, resp);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = productService.findAll();
        req.setAttribute("productList", productList);
        req.getRequestDispatcher("/views/product/list.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);

        if (product != null) {
            req.setAttribute("product", product);
            req.getRequestDispatcher("/views/product/edit.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/products?mess=Product not found");
        }
    }

    private void searchByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        List<Product> searchResults = productService.searchByName(name);
        req.setAttribute("productList", searchResults);
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
            case "edit":
                updateProduct(req, resp);
                break;
            case "delete":
                deleteById(req, resp);
                break;
            default:
        }
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String manufacturer = req.getParameter("manufacturer");

            double price = 0.0;
            if (req.getParameter("price") != null && !req.getParameter("price").trim().isEmpty()) {
                price = Double.parseDouble(req.getParameter("price"));
            }

            Product product = new Product(name, price, description, manufacturer);
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
    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            String description = req.getParameter("description");
            String manufacturer = req.getParameter("manufacturer");

            Product product = new Product(id, name, price, description, manufacturer);
            boolean isUpdated = productService.update(product);

            if (isUpdated) {
                resp.sendRedirect("/products?mess=Update success");
            } else {
                req.setAttribute("error", "Cập nhật thất bại!");
                req.getRequestDispatcher("/views/product/edit.jsp").forward(req, resp);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Lỗi nhập liệu. Vui lòng kiểm tra lại.");
            req.getRequestDispatcher("/views/product/edit.jsp").forward(req, resp);
        }
    }
}
