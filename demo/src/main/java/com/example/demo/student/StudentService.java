package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// *** SERVICE LAYER *** mainly responsible for business logic. 2nd Level


// We have to tell that this StudentService is a class that has to be instantiated.
// i.e. It has to be a Spring bean
// To tell that this class is a Spring bean we use @Service

// It is used at the class level. It shows that the annotated class is a service class, 
// such as business basic logic, and call external APIs.
@Service
public class StudentService {


	private final StudentRepository studentRepository;

	// injecting instance of Class StudentRepository into class StudentService 
	// so that you can access StudenRepository's instance variables and methods. 

	// This annotation is used to auto-wire spring bean on setter methods, constructor and instance variable. 
	// It injects object dependency implicitly. When we use this annotation, 
	// the spring container auto-wires the bean by its matching data type.
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	
	// GET logic
	// method will be called in StudentController
	// LOGIC here is .findAll which returns a list
	public List<Student> getStudents() {
		return studentRepository.findAll();

	}

	// POST logic
	// method uses Optional type Student to first check if student exists
	// if condition, checks if student is present. 
	// The data is passed to this method by StudentController class registerNewStudent method.
	// student argument contains web request body, which contains name, email etc...
	public void addNewStudent(Student student) {
	 Optional<Student> studentOptional =	
			 studentRepository.findStudentByEmail(student.getEmail());
	 
	 if(studentOptional.isPresent()) {
		 throw new IllegalStateException("Email taken");
	 }
	 
	 studentRepository.save(student);
		
		}

	
	// DELETE logic
	// method that deletes student by studentId 
	// checks in studentRepository if "id" exists by using .existsById
	// if "id" does exist, we run 
	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		
		// !exists = does not exist - opposite.
		if(!exists) {
			throw new IllegalStateException("student with id " + studentId + " does not exist");
		}
		
		studentRepository.deleteById(studentId);
	}
	
	
	
	// PUT LOGIC
	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		
		Student student = studentRepository.findById(studentId).orElseThrow(()
				-> new IllegalStateException("student with id " + studentId + " does not exist"));
				
		// !Objects.equals(student.getName(), name) = if the name provided is not the same as the current one
		if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
			
		}
		
		if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			
			if(studentOptional.isPresent()) {
				throw new IllegalStateException("Email taken");
			}
			student.setEmail(email);
		}
		
	}
		
		
		
	
}























