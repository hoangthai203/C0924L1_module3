package com.codegym.case_study.controller;

import com.codegym.case_study.model.User;
import com.codegym.case_study.service.LoginService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dang-nhap")
public class LoginController extends HttpServlet {
    private LoginService userService = new LoginService(); // Không cần IUserService

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tenDangNhap = request.getParameter("tenDangNhap");
        String matKhau = request.getParameter("matKhau");

        User user = userService.kiemTraDangNhap(tenDangNhap, matKhau);
        HttpSession session = request.getSession();

        if (user != null) {
            session.setAttribute("loggedUser", user);
            session.setAttribute("successMessage", "🎉 Đăng nhập thành công! Chào mừng " + user.getTenDangNhap() + "!");
            response.sendRedirect("/phone"); // Điều hướng về danh sách sản phẩm
        } else {
            request.setAttribute("errorMessage", "⚠ Sai tên đăng nhập hoặc mật khẩu!");
            request.getRequestDispatcher("/views/product/dang-nhap.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/product/dang-nhap.jsp").forward(request, response);
    }
}
