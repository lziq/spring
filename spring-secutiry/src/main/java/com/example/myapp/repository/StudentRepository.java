package com.example.myapp.repository;

import com.example.myapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="members")
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
