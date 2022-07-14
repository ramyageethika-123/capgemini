package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.entity.Teacher;



public interface TeacherService {
	
	List<Teacher> getAllTeachers();
	
	Teacher saveTeacher(Teacher teacher);
	Teacher updateTeacher(Teacher teacher);
	Teacher getTeacherById(Long tid);
	
	void deleteTeacherById(Long tid);
	
	List<Teacher> findAllTeachers(String keyword);
	
	Page<Teacher> findPaginated(int pageNo, int pageSize,String sortField, String sortDirection);



}