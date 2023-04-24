package com.example.myapp;

import com.example.myapp.model.Student;
import com.example.myapp.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentRepository studentRepository){
		return runner -> {
			createStudent(studentRepository);
		};
	}

	private void createStudent(StudentRepository studentRepository) {
		studentRepository.save(new Student("ciki", "li", "123@gmail"));
		studentRepository.save(new Student("ciki12", "li12", "12312@gmail"));
	}
}
