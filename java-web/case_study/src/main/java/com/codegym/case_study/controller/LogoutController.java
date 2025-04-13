package com.codegym.case_study.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        // Lưu thông báo trước khi xóa session
        session.setAttribute("successMessage", "🚪 Bạn đã đăng xuất thành công!");

        // Xóa toàn bộ session
        session.invalidate();

        // Chuyển hướng về trang danh sách sản phẩm
        response.sendRedirect(request.getContextPath() + "/phone");
    }
}
