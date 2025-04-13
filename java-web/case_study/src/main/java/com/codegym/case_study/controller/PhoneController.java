package com.codegym.case_study.controller;

import com.codegym.case_study.model.Phone;
import com.codegym.case_study.service.IPhoneService;
import com.codegym.case_study.service.PhoneService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PhoneController", urlPatterns = {"/phone", "/phone/detail", "/phone/search"})
public class PhoneController extends HttpServlet {
    private final IPhoneService phoneService = new PhoneService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/phone":
                showPhoneList(request, response);
                break;
            case "/phone/detail":
                showPhoneDetail(request, response);
                break;
            case "/phone/search":
                timKiemDienThoai(request, response);
                break;
            default:
                response.sendRedirect("/phone");
        }
    }

    private void showPhoneList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Phone> phoneList = phoneService.findAll();
        request.setAttribute("phoneList", phoneList);
        request.getRequestDispatcher("/views/product/danh_sach_san_pham.jsp").forward(request, response);
    }

    private void showPhoneDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Phone phone = phoneService.findById(id);
        if (phone != null) {
            request.setAttribute("phone", phone);
            request.getRequestDispatcher("/views/product/chi_tiet_san_pham.jsp").forward(request, response);
        } else {
            response.sendRedirect("/phone");
        }
    }

    private void timKiemDienThoai(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Phone> danhSachTimKiem = phoneService.timKiemDienThoai(keyword);
        request.setAttribute("phoneList", danhSachTimKiem);
        request.getRequestDispatcher("/views/product/danh_sach_san_pham.jsp").forward(request, response);
    }
}
