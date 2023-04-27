package com.example.myapp.controller;

import com.example.myapp.Coach;
import com.example.myapp.dao.StudentDAO;
import com.example.myapp.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FunRestController {

    private Coach coach;

    @Autowired
    FunRestController(@Qualifier("cricketCoach") Coach coach){
        this.coach = coach;
    }

    @GetMapping("/dailyworkout/")
    public String getDailWorkout(){
        return coach.getDailyWorkout();
    }

    @Value("${coach.name}")
    private String coachName;

    @GetMapping("/students/")
    public List<Student> getAllEmployees(StudentDAO studentDAO){
        return studentDAO.queryAll();
    }
}
