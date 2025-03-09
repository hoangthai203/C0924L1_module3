package com.codegym.ss10_jstl.repository;

import com.codegym.ss10_jstl.model.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> findAll();
    boolean add(Student student);
}