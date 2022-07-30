package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// It consists of @Configuration, @ComponentScan, and @EnabeAutoConfiguration. 
// The class annotated with @SpringBootApplication is kept in the base package. 
// This annotation does the component scan. However, only the sub-packages are scanned. 
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	

}
