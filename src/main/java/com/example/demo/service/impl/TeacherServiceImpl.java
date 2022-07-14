package com.example.demo.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Teacher;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	private TeacherRepository teacherRepository;

	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		super();
		this.teacherRepository = teacherRepository;
	}

	@Override
	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public Teacher saveTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public Teacher updateTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public Teacher getTeacherById(Long tid) {
		return teacherRepository.findById(tid).get();
	}

	@Override
	public void deleteTeacherById(Long tid) {
		teacherRepository.deleteById(tid);
		
	}

	@Override
	public List<Teacher> findAllTeachers(String keyword) {
		if (keyword != null) {
            return teacherRepository.search(keyword);
        }
        return (List<Teacher>) teacherRepository.findAll();
	}

	@Override
	public Page<Teacher> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo -1, pageSize,sort);
		return this.teacherRepository.findAll(pageable);
	}
}