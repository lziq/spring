package com.example.myapp.controller;

import com.example.myapp.entity.Student;
import com.example.myapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/students/{id}")
    public Student queryStudent(@PathVariable int id){
        return studentService.findById(id);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student s){
        s.setId(0);

        return studentService.save(s);
    }

    @PutMapping ("/students")
    public Student updateStudent(@RequestBody Student s){
        return studentService.save(s);
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id){
        studentService.deleteById(id);

        return "Deleted student with id: " + id;
    }
}
