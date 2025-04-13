package com.codegym.furama.controller;

import com.codegym.furama.model.Customer;
import com.codegym.furama.service.CustomerService;
import com.codegym.furama.service.ICustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/customer")
public class CustomerController extends HttpServlet {
    private ICustomerService customerService = new CustomerService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteCustomer(request, response);
                break;
            default:
                showList(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "create":
                insertCustomer(request, response);
                break;
            case "edit":
                updateCustomer(request, response);
                break;
        }
    }

    private void showList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = request.getParameter("search");
        if (search == null) search = "";

        int page = 1;
        int recordsPerPage = 5;

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            // mặc định page = 1
        }

        int offset = (page - 1) * recordsPerPage;

        List<Customer> customers = customerService.searchByNamePaging(search, offset, recordsPerPage);
        int totalRecords = customerService.countTotalCustomerByName(search);
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        request.setAttribute("customers", customers);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("search", search);

        request.getRequestDispatcher("view/customer/list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/customer/create.jsp").forward(request, response);
    }

    private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("id");
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        String name = request.getParameter("name");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String idCard = request.getParameter("idCard");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        Customer customer = new Customer(id, typeId, name, birthday, gender, idCard, phone, email, address);
        customerService.save(customer);
        response.sendRedirect("/customer");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Customer customer = customerService.findById(id);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("view/customer/edit.jsp").forward(request, response);
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("id");
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        String name = request.getParameter("name");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String idCard = request.getParameter("idCard");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        Customer customer = new Customer(id, typeId, name, birthday, gender, idCard, phone, email, address);
        customerService.update(customer);
        response.sendRedirect("/customer");
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("id");
        customerService.remove(id);
        response.sendRedirect("/customer");
    }
}