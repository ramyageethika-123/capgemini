package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

	
	
	@Query("SELECT t FROM Teacher t WHERE CONCAT(t.name, ' ', t.email,' ') LIKE %?1%")
	public List<Teacher> search(String keyword);

}