package com.codegym.case_study.controller;

import com.codegym.case_study.model.DichVu;
import com.codegym.case_study.service.DichVuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DichVuController", urlPatterns = "/dichvu")
public class DichVuController extends HttpServlet {
    private DichVuService dichVuService = new DichVuService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "add":
                    showAddForm(request, response);
                    break;
                case "delete":
                    deleteDichVu(request, response);
                    break;
                case "suspend":
                    suspendDichVu(request, response);
                    break;
                default:
                    listDichVu(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Điều hướng đến trang lỗi nếu có vấn đề SQL
        }
    }

    private void listDichVu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<DichVu> dichVuList = dichVuService.layTatCaDichVu();
        request.setAttribute("dichVuList", dichVuList);
        request.getRequestDispatcher("/views/product/dichvu.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/product/add.jsp").forward(request, response);
    }

    private void deleteDichVu(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        dichVuService.xoaDichVu(id);
        response.sendRedirect("/dichvu");
    }

    private void suspendDichVu(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        dichVuService.capNhatTrangThaiDichVu(id, "tam_ngung");
        response.sendRedirect("/dichvu");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                addDichVu(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void addDichVu(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String ten = request.getParameter("ten");
        String moTa = request.getParameter("mo_ta");
        double gia = Double.parseDouble(request.getParameter("gia"));

        DichVu dichVu = new DichVu(0, ten, moTa, gia, "hoat_dong");
        dichVuService.themDichVu(dichVu);
        response.sendRedirect("/dichvu");
    }
}
