package com.example.demo.student;

import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// *** API LAYER *** 1st Level
// This class will have all the resources for our API.



// RequestMapping is used to map the HTTP request. It is used with the class as well as the method. 
// It has many other optional elements like consumes, name, method, request, path, etc.
@RestController
@RequestMapping(path = "student") // alter URL add path student. localhost/8080/student
public class StudentController {
	
	//reference - we create a reference from StudentService class 
	private final StudentService studentService;
	
	// constructor - anything we pass into this constructor should be injected
	// use @Autowired - magically instantiated 
	@Autowired
	public StudentController(StudentService studentService) {
		// traditional way of passing object and instantiating by using the new keyword
		// dont use this method anymore, because we use dependency injection now
//		this.studentService = new studentService();
		this.studentService = studentService;
	}
	
	
	// ** GET **
	// in order for this method to be served as an restful endpoint, annotate @GetMapping
	// Now that we have an endpoint, restart the server and refresh the browser.
	// Note here we are using Student object type
	@GetMapping
	public List<Student> getStudents() { // use getStudents from StudentService class
		return studentService.getStudents();
		
	};
	
	
	// ** POST **
	// Using PostMapping here to save student to database
	// registerNewStudent method first checks if a student exists by checking email
	// Take @RequestBody and map it into Student, so now Student will contain the web request data 
	// inside the web request is JSON info which we can extract i.e. name, email, dob...
	// will use this method to check if email exists in db, if null we will registerNewStudent
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}
	
	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
	}
	
	@PutMapping
	public void updateStudentEmail(
			@PathVariable("studentId") Long studentId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		studentService.updateStudent(studentId, name, email);
		
	}
	
}
