package com.codegym.case_study.controller;

import com.codegym.case_study.model.Cart;
import com.codegym.case_study.model.User;
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
        String action = request.getServletPath();

        switch (action) {
            case "/cart/add":
                themSanPhamVaoGio(request, response);
                break;
            case "/cart/remove":
                xoaSanPhamKhoiGio(request, response);
                break;
            case "/cart/update":
                capNhatSoLuong(request, response);
                break;
            case "/cart/clear":
                xoaToanBoGio(request, response);
                break;
            case "/cart":
            default:
                xemGioHang(request, response);
                break;
        }
    }

    private Integer getIdNguoiDung(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
            System.out.println("Không tìm thấy người dùng trong session");
            return null;
        }
        System.out.println("ID người dùng: " + user.getId());
        return user.getId();
    }

    private void xemGioHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idNguoiDung = getIdNguoiDung(request);
        System.out.println("Xem giỏ hàng - ID người dùng: " + idNguoiDung);

        Cart gioHang = cartService.layGioHang(idNguoiDung);
        System.out.println("Số sản phẩm trong giỏ: " + (gioHang != null ? gioHang.getDanhSachSanPham().size() : 0));

        request.setAttribute("gioHang", gioHang);
        request.getRequestDispatcher("/views/product/cart.jsp").forward(request, response);
    }

    private void themSanPhamVaoGio(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Đang thêm sản phẩm vào giỏ...");
        Integer idNguoiDung = getIdNguoiDung(request);
        if (idNguoiDung == null) {
            System.out.println("Người dùng chưa đăng nhập");
            response.sendRedirect(request.getContextPath() + "/dang-nhap");
            return;
        }

        int idSanPham = Integer.parseInt(request.getParameter("idSanPham"));
        int soLuong = Integer.parseInt(request.getParameter("soLuong"));
        System.out.println("idSanPham: " + idSanPham + ", soLuong: " + soLuong);

        cartService.themSanPhamVaoGio(idNguoiDung, idSanPham, soLuong);
        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private void xoaSanPhamKhoiGio(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer idNguoiDung = getIdNguoiDung(request);
        int idSanPham = Integer.parseInt(request.getParameter("idSanPham"));

        cartService.xoaSanPhamKhoiGio(idNguoiDung, idSanPham);
        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private void capNhatSoLuong(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer idNguoiDung = getIdNguoiDung(request);
        int idSanPham = Integer.parseInt(request.getParameter("idSanPham"));
        int soLuongMoi = Integer.parseInt(request.getParameter("soLuong"));

        cartService.capNhatSoLuong(idNguoiDung, idSanPham, soLuongMoi);
        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private void xoaToanBoGio(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer idNguoiDung = getIdNguoiDung(request);
        cartService.xoaToanBoGioHang(idNguoiDung);
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
