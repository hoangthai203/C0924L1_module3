package com.codegym.ss10_jstl.controller;

import com.codegym.ss10_jstl.model.Student;
import com.codegym.ss10_jstl.service.IStudentService;
import com.codegym.ss10_jstl.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudentServlet",value = "/students")
public class StudentServlet  extends HttpServlet {
    private IStudentService studentService = new StudentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = studentService.findAll();
        req.setAttribute("studentList", studentList);
        req.getRequestDispatcher("/views/student/list.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}