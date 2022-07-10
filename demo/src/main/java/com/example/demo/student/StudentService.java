package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;

// *** SERVICE LAYER *** mainly responsible for business logic.

public class StudentService {
	
	// method will be called in StudentController
	public List<Student> getStudents() {
	// Will return a JSON array list
			return List.of(
			new Student(123L, "Akram", "Akram@gmail.com", LocalDate.of(1984, 05, 01) , 38));
	}
}
