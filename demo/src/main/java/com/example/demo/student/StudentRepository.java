package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// ****** DATA ACCESS LAYER ****** 3rd Level

// This interface is responsible for data access.

// Want to use this Interface inside of our StudentService class

// Type = Student object, Long = Id


// It is a Data Access Object (DAO) that accesses the database directly. 
// It indicates that the annotated class is a repository. 
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	
	
	// ** Optional = Optional is primarily intended for use as a method return type where 
	// there is a clear need to represent 'no result,' and where using 
	// null is likely to cause errors *** 
	
	
	// Custom function that will find a user by email
	// This will transform into an SQL such as:
	// SELECT * FROM student WHERE email = ?;
	@Query("SELECT s FROM Student s WHERE s.email = ?1") // Student is entity
	Optional<Student> findStudentByEmail(String email);

	
}
