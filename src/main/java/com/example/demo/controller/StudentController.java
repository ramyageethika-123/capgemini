package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.servlet.ModelAndView;

//import com.example.demo.entity.Contact;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@Controller
public class StudentController {
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	//handler
	
	@RequestMapping("/login")
	public String loginPage(Model model) {
		return "login";
	}
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		return "students";
	}
	
	@GetMapping("/students")
	public String listStudents(Model model) {
		//model.addAttribute("students",studentService.getAllStudents());
		//return "students";
		return findPaginated(1, "firstName", "asc",model);
		
	}
	
	@GetMapping("/students/search")
    public String viewHome(Model model, @Param("keyword") String keyword) {
        List<Student> getst = studentService.findAllStudents(keyword);
        model.addAttribute("getst", getst);
        model.addAttribute("keyword", keyword);
         
        return "students";
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
	 
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		//create student to hold student form data
		Student student=new Student();
		model.addAttribute("student",student);
		return "create_student";
		
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,
		@ModelAttribute("student") Student student, Model model) {
		
		//get student from database by id
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setDob(student.getDob());
		existingStudent.setPhone(student.getPhone());
		existingStudent.setAddress(student.getAddress());
		existingStudent.setYoj(student.getYoj());
		
		//save updated student object
		studentService.updateStudent(existingStudent);
		return "redirect:/students";
	}
	
	//handler method to delete
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo,@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 3;
		
		Page<Student> page = studentService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Student> students = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("students", students);
		return "students";
	}

}