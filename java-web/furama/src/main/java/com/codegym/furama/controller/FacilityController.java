package com.codegym.furama.controller;

import com.codegym.furama.model.Facility;
import com.codegym.furama.service.FacilityService;
import com.codegym.furama.service.IFacilityService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FacilityServlet", urlPatterns = "/facility")
public class FacilityController extends HttpServlet {
    private final IFacilityService facilityService = new FacilityService();

    @Override
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
                deleteFacility(request, response);
                break;
            default:
                listFacilities(request, response);
                break;
        }
    }

    private void listFacilities(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Facility> facilities = facilityService.findAll();
        request.setAttribute("facilityList", facilities);
        request.getRequestDispatcher("/view/facility/list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/facility/create.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Facility facility = facilityService.findById(id);
        request.setAttribute("facility", facility);
        request.getRequestDispatcher("/view/facility/edit.jsp").forward(request, response);
    }

    private void deleteFacility(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        facilityService.delete(id);
        response.sendRedirect("/facility");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "create":
                createFacility(request, response);
                break;
            case "edit":
                updateFacility(request, response);
                break;
        }
    }

    private void createFacility(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Facility facility = getFacilityFromRequest(request);
        facilityService.save(facility);
        response.sendRedirect("/facility");
    }

    private void updateFacility(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Facility facility = getFacilityFromRequest(request);
        facilityService.update(facility);
        response.sendRedirect("/facility");
    }

    private Facility getFacilityFromRequest(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double area = Double.parseDouble(request.getParameter("area"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
        String rentType = request.getParameter("rentType");
        String facilityType = request.getParameter("facilityType");

        return new Facility(id, name, area, cost, maxPeople, rentType, facilityType);
    }
}
