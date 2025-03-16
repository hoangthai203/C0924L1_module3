package com.codegym.ss10_jstl.service;

import com.codegym.ss10_jstl.model.Student;
import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    boolean add(Student student);
}
