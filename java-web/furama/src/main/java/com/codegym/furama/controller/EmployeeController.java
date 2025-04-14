package com.codegym.furama.controller;

import com.codegym.furama.model.Employee;
import com.codegym.furama.repository.DivisionRepository;
import com.codegym.furama.repository.EducationDegreeRepository;
import com.codegym.furama.repository.PositionRepository;
import com.codegym.furama.service.EmployeeService;
import com.codegym.furama.service.IEmployeeService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "EmployeeServlet", urlPatterns = "/employee")
public class EmployeeController extends HttpServlet {
    private final IEmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                deleteEmployee(request, response);
                break;
            default:
                showList(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "create":
                createEmployee(request, response);
                break;
            case "edit":
                updateEmployee(request, response);
                break;
        }
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = employeeService.findAll();
        request.setAttribute("employees", employees);
        request.setAttribute("positions", new PositionRepository().findAll());
        request.setAttribute("educationDegrees", new EducationDegreeRepository().findAll());
        request.setAttribute("divisions", new DivisionRepository().findAll());
        request.getRequestDispatcher("view/employee/list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("positions", new PositionRepository().findAll());
        request.setAttribute("educationDegrees", new EducationDegreeRepository().findAll());
        request.setAttribute("divisions", new DivisionRepository().findAll());
        request.getRequestDispatcher("view/employee/create.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Employee employee = employeeService.findById(id);
        request.setAttribute("employee", employee);
        request.setAttribute("positions", new PositionRepository().findAll());
        request.setAttribute("educationDegrees", new EducationDegreeRepository().findAll());
        request.setAttribute("divisions", new DivisionRepository().findAll());
        request.getRequestDispatcher("view/employee/edit.jsp").forward(request, response);
    }

    private void createEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Employee e = getEmployeeFromRequest(request);
        employeeService.save(e);
        response.sendRedirect("/employee");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Employee e = getEmployeeFromRequest(request);
        employeeService.update(e);
        response.sendRedirect("/employee");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        employeeService.remove(id);
        response.sendRedirect("/employee");
    }

    private Employee getEmployeeFromRequest(HttpServletRequest req) {
        return new Employee(
                req.getParameter("id"),
                req.getParameter("name"),
                Date.valueOf(req.getParameter("birthday")),
                req.getParameter("idCard"),
                Double.parseDouble(req.getParameter("salary")),
                req.getParameter("phone"),
                req.getParameter("email"),
                req.getParameter("address"),
                Integer.parseInt(req.getParameter("positionId")),
                Integer.parseInt(req.getParameter("educationId")),
                Integer.parseInt(req.getParameter("divisionId"))
        );
    }
}

