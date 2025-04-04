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
import java.util.List;

@WebServlet(name = "StudentServlet",value = "/students")
public class StudentController extends HttpServlet {
    private IStudentService studentService = new StudentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action ==null){
            action = "";
        }
        switch (action){
            case "create":
                // trả về form thê mới
                showFormCreate(req,resp);
                break;
            case "delete":
                // xoá
                break;
            case "update":
                // update
                break;
            default:
                // trả về list
                showList(req,resp);

        }

    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/views/student/create.jsp").forward(req,resp);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = studentService.findAll();
        req.setAttribute("studentList", studentList);
        req.getRequestDispatcher("/views/student/list.jsp").forward(req,resp);
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action ==null){
            action = "";
        }
        switch (action){
            case "create":
                // trả về form thê mới
                // gọi xuống service để thê mới
                save(req,resp);
                break;
            case "delete":
                // xoá
                break;
            case "update":
                // update
                break;
            default:

        }
    }
    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
        float score = Float.parseFloat(req.getParameter("score"));
        int classId = Integer.parseInt(req.getParameter("classId"));
        Student student = new Student(id,name,gender,score,classId);
        boolean flag = studentService.add(student);
        if (flag){
            // thêm mới thành công
            resp.sendRedirect("/students?mess=Created succes");
        }else {
            // không thaành công trả vè form thêm mới
            showFormCreate(req,resp);
        }

    }
}