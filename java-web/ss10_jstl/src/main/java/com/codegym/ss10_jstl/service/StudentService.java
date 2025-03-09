package com.codegym.ss10_jstl.service;

import com.codegym.ss10_jstl.model.Student;
import com.codegym.ss10_jstl.repository.IStudentRepository;
import com.codegym.ss10_jstl.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public class StudentService implements IStudentService{
    // xử lý nghiệp vụ:
    private IStudentRepository studentRepository = new StudentRepository();
    @Override
    public List<Student> findAll() {
        // gọi repository ( lấy dữ liệu)
        return studentRepository.findAll();
    }

    @Override
    public boolean add(Student student) {
        studentRepository.add(student);
        return true;
    }
}