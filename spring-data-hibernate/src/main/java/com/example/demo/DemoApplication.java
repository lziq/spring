package com.example.demo;

import com.example.demo.dao.CourseRepository;
import com.example.demo.dao.InstructorDetailRepository;
import com.example.demo.dao.InstructorRepository;
import com.example.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			InstructorRepository instructorRepository,
			InstructorDetailRepository instructorDetailRepository,
			CourseRepository courseRepository
	){
		InstructorDetail instructorDetail = new InstructorDetail("http://www.whyyousogood.com/youtube", "dota2");
		Instructor instructor = new Instructor("ciki", "li", "cikili@gmail.com");
		instructor.setInstructorDetail(instructorDetail);

		Course course1 = new Course("Dota2 - how to make your teammates troll");
		Course course2 = new Course("Hollow Knight - how to delete the game");
		instructor.add(course1);
		instructor.add(course2);
		// we don't need the following since Hibernate does it for us
		// course1.setInstructor(instructor);
		// course2.setInstructor(instructor);

		course2.addReview(new Review("Great course"));
		course2.addReview(new Review("Cool course"));
		course2.addReview(new Review("What a dumb course"));

		Student student1 = new Student("John", "Doe", "johndoe@gamil.com");
		Student student2 = new Student("Mary", "Public", "marypublic@gmail.com");
		course2.addStudent(student1);
		course2.addStudent(student2);

		return runner -> {
			instructorRepository.save(instructor); // Saves everything
			courseRepository.deleteById(course1.getId()); // Won't delete the instructor

//			InstructorDetail instructorDetail1 = instructorDetailRepository.findById(20).get();
//			Instructor instructor1 = instructorRepository.findById(21).get();
//
//			System.out.println(instructorDetail1.getInstructor() == instructor1);
//			System.out.println(instructorDetail1 == instructor1.getInstructorDetail());

		};
	}
}
