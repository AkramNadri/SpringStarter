package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// *** API LAYER ***
// This class will have all the resources for our API.


@RestController
@RequestMapping(path = "student") // alter URL add path student. localhost/8080/student
public class StudentController {
	
	//reference - we create a reference from StudentService class 
	private final StudentService studentService;
	
	// constructor - anything we pass into this constructor should be injected
	// use @Autowired - magicalled instantiated 
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping
	// in order for this method to be served as an restful endpoint, annotate @GetMapping
	// Now that we have an endpoint, restart the server and refresh the browser.
	// Note here we are using Student object type
	public List<Student> getStudents() { // use getStudents from StudentService class
		return studentService.getStudents();
		
	};
	
}
