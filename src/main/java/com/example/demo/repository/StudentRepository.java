package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findAll();
	
	@Query("SELECT s FROM Student s WHERE CONCAT(s.firstName, ' ', s.lastName,' ', s.email,' ') LIKE %?1%")
	public List<Student> search(String keyword);
	

}