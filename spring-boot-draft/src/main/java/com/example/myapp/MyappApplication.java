package com.example.myapp;

import com.example.myapp.dao.StudentDAO;
import com.example.myapp.entity.Student;
import com.example.myapp.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentService studentService){
		return runner -> {
			createStudent(studentService);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			//queryAllStudent(studentDAO);
		};
	}

	private void createStudent(StudentService studentService) {
		studentService.save(new Student("ciki", "li", "123@gmail"));
		studentService.save(new Student("ciki12", "li12", "12312@gmail"));
	}

	private void updateStudent(StudentService studentService) {
		Student s = studentService.findById(1);
		s.setEmail("321321@gmail.com");
		studentService.save(s);
	}

	private void deleteStudent(StudentService studentService) {
		studentService.deleteById(2);
	}

	private void queryAllStudent(StudentService studentService){
		List<Student> students = studentService.findAll();
		for (Student s : students){
			System.out.println(s.getId());
		}
	}
}
