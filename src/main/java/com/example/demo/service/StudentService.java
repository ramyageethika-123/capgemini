package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.entity.Student;

public interface StudentService {
	List<Student> getAllStudents();
	
	Student saveStudent(Student student);
	Student updateStudent(Student student);
	Student getStudentById(Long id);
	
	void deleteStudentById(Long id);
	
	List<Student> findAllStudents(String keyword);
	
	Page<Student> findPaginated(int pageNo, int pageSize,String sortField, String sortDirection);
}