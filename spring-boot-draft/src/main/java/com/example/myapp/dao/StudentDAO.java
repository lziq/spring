package com.example.myapp.dao;

import com.example.myapp.entity.Student;

import java.util.List;

public interface StudentDAO {
    Student save (Student s);

    Student findById(int id);

    void delete (Student s);

    List<Student> queryAll();
}
