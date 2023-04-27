package com.example.myapp.service;

import com.example.myapp.dao.StudentDAO;
import com.example.myapp.entity.Student;
import com.example.myapp.error.StudentsNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO){
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> findAll() {
        return studentDAO.queryAll();
    }

    @Override
    public Student findById(int id) {
        if (id > studentDAO.queryAll().size()){
            throw new StudentsNotFoundException("student id not found");
        }

        return studentDAO.findById(id);
    }

    @Override
    @Transactional
    public Student save(Student s) {
        return studentDAO.save(s);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        studentDAO.delete(studentDAO.findById(id));
    }
}
