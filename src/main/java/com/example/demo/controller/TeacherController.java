package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Teacher;
import com.example.demo.service.TeacherService;

@Controller
public class TeacherController {
	
	private TeacherService teacherService;

	public TeacherController(TeacherService teacherService) {
		super();
		this.teacherService = teacherService;
	}
	//handler
	
	@GetMapping("/teachers")
	public String listTeachers(Model model) {
		//model.addAttribute("students",studentService.getAllStudents());
		//return "students";
		return findPaginated(1, "name", "asc",model);
		
	}
	
	@GetMapping("/teachers/search")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Teacher> getst = teacherService.findAllTeachers(keyword);
        model.addAttribute("getst", getst);
        model.addAttribute("keyword", keyword);
         
        return "teachers";
    }
	// contact
	
	/*@RequestMapping("/students/contact/{id}") 
	public ModelAndView viewContact(@PathVariable(name= "id") long id) { 
		ModelAndView mav=new ModelAndView("view_contact"); 
		ResponseEntity <Contact> responseEntity= new RestTemplate().getForEntity("http://localhost:8082/contact/students/{studentId}",Contact.class,mav); 
		Contact contact=responseEntity.getBody(); 
		mav.addObject("contact", contact); 
		return mav; 
		
	}*/
	 
	@GetMapping("/teachers/new")
	public String createTeacherForm(Model model) {
		//create student to hold student form data
		Teacher teacher=new Teacher();
		model.addAttribute("teacher",teacher);
		return "create_teacher";
		
	}
	
	@PostMapping("/teachers")
	public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
		teacherService.saveTeacher(teacher);
		return "redirect:/teachers";
	}
	
	@GetMapping("/teachers/edit/{tid}")
	public String editTeacherForm(@PathVariable Long tid, Model model) {
		model.addAttribute("teacher", teacherService.getTeacherById(tid));
		return "edit_teacher";
	}
	
	@PostMapping("/teachers/{tid}")
	public String updateTeacher(@PathVariable Long tid,
		@ModelAttribute("teacher") Teacher teacher, Model model) {
		
		//get student from database by id
		Teacher existingTeacher = teacherService.getTeacherById(tid);
		existingTeacher.setTid(tid);
		existingTeacher.setName(teacher.getName());
		existingTeacher.setEmail(teacher.getEmail());
		existingTeacher.setDob(teacher.getDob());
		existingTeacher.setPhone(teacher.getPhone());
		existingTeacher.setAddress(teacher.getAddress());
		
		
		//save updated student object
		teacherService.updateTeacher(existingTeacher);
		return "redirect:/teachers";
	}
	
	//handler method to delete
	@GetMapping("/teachers/{tid}")
	public String deleteTeacher(@PathVariable Long tid) {
		teacherService.deleteTeacherById(tid);
		return "redirect:/teachers";
	}
	
	
	
	@GetMapping("/page/{pNo}")
	public String findPaginated(@PathVariable (value = "pNo") int pageNo,@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;
		
		Page<Teacher> page = teacherService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Teacher> teachers = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("teachers", teachers);
		return "teachers";
	}
	

}