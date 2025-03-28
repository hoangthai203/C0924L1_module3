package com.codegym.case_study.controller;

import com.codegym.case_study.model.Cart;
import com.codegym.case_study.service.CartService;
import com.codegym.case_study.service.ICartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CartController", urlPatterns = {"/cart", "/cart/add", "/cart/remove", "/cart/update", "/cart/clear"})
public class CartController extends HttpServlet {
    private ICartService cartService;

    @Override
    public void init() throws ServletException {
        cartService = new CartService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "add":
                themSanPhamVaoGio(request, response);
                break;
            case "remove":
                xoaSanPhamKhoiGio(request, response);
                break;
            case "update":
                capNhatSoLuong(request, response);
                break;
            case "clear":
                xoaToanBoGio(request, response);
                break;
            default:
                xemGioHang(request, response);
                break;
        }
    }

    private void xemGioHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Nếu không có idNguoiDung, đặt giá trị mặc định -1
        Integer idNguoiDung = (Integer) session.getAttribute("idNguoiDung");
        if (idNguoiDung == null) {
            idNguoiDung = -1;
            session.setAttribute("idNguoiDung", idNguoiDung);
        }

        Cart gioHang = cartService.layGioHang(idNguoiDung);
        request.setAttribute("gioHang", gioHang);
        request.getRequestDispatcher("/views/product/cart.jsp").forward(request, response);
    }

    private void themSanPhamVaoGio(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Integer idNguoiDung = (Integer) session.getAttribute("idNguoiDung");

        if (idNguoiDung == null) {
            idNguoiDung = -1;
            session.setAttribute("idNguoiDung", idNguoiDung);
        }

        int idSanPham = Integer.parseInt(request.getParameter("id"));
        int soLuong = Integer.parseInt(request.getParameter("quantity"));

        cartService.themSanPhamVaoGio(idNguoiDung, idSanPham, soLuong);
        response.sendRedirect("cart");
    }

    private void xoaSanPhamKhoiGio(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Integer idNguoiDung = (Integer) session.getAttribute("idNguoiDung");

        if (idNguoiDung == null) {
            idNguoiDung = -1;
            session.setAttribute("idNguoiDung", idNguoiDung);
        }

        int idSanPham = Integer.parseInt(request.getParameter("id"));
        cartService.xoaSanPhamKhoiGio(idNguoiDung, idSanPham);
        response.sendRedirect("cart");
    }

    private void capNhatSoLuong(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Integer idNguoiDung = (Integer) session.getAttribute("idNguoiDung");

        if (idNguoiDung == null) {
            idNguoiDung = -1;
            session.setAttribute("idNguoiDung", idNguoiDung);
        }

        int idSanPham = Integer.parseInt(request.getParameter("id"));
        int soLuongMoi = Integer.parseInt(request.getParameter("quantity"));

        cartService.capNhatSoLuong(idNguoiDung, idSanPham, soLuongMoi);
        response.sendRedirect("cart");
    }

    private void xoaToanBoGio(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Integer idNguoiDung = (Integer) session.getAttribute("idNguoiDung");

        if (idNguoiDung == null) {
            idNguoiDung = -1;
            session.setAttribute("idNguoiDung", idNguoiDung);
        }

        cartService.xoaToanBoGioHang(idNguoiDung);
        response.sendRedirect("cart");
    }
}

