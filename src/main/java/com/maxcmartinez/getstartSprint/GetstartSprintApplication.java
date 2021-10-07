package com.maxcmartinez.getstartSprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
//@RestController
public class GetstartSprintApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetstartSprintApplication.class, args);
	}
	/*@GetMapping
	public List<Student> getGreating(){
		return List.of(
				new Student(
						1L,
						"Marian",
						"marian@gmail.com",
						LocalDate.of(2000, Month.APRIL,16),
						21
				)
		);
	}*/
}
