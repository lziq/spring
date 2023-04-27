package com.example.myapp.service;

import com.example.myapp.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student findById(int id);

    Student save(Student s);

    void deleteById(int id);
}
