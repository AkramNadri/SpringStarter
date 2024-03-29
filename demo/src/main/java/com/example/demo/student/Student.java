package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.sound.midi.Sequence;

// To map this Student to our database:
@Entity // Entity is for Hibernate
@Table // Table is for table in our database
public class Student {

	// ***** Map Student class to our database *****
	// Generates a Primary key
	@Id
	@SequenceGenerator (
			name = "student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1
			)
	
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence"
			)
	
		
	
	private Long id;
	private String name;
	private String email;
	private LocalDate dob;
	
	
	// This field, there is no need to be a column in our database. 
	@Transient
	private Integer age;

	// No arg constructor
	public Student() {	
	}

	// Constructor
	public Student(Long id, String name, String email, 
			LocalDate dob){
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
	}

	// Constructor without ID since DATABASE will generate an ID (primary key)
	public Student(String name, String email, 
			LocalDate dob){
		this.name = name;
		this.email = email;
		this.dob = dob;
	}



	public Long getId () {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email=email;
	}

	public LocalDate getDob() {
		return dob;
	}


	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Integer getAge() {
		return Period.between(this.dob, LocalDate.now()).getYears();
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + 
				", dob=" + dob + ", age=" + age + "]";
	}

}
