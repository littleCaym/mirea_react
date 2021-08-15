package mirea.react.lab1_5.controller;

import mirea.react.lab1_5.dao.StudentJdbc;
import mirea.react.lab1_5.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	private final StudentJdbc studentJdbc;

	public StudentController(StudentJdbc studentJdbc){this.studentJdbc = studentJdbc;}


	//Выдает либо по фамилии, либо по id
	@GetMapping("/student/{key}")
	public Student getStudent(@PathVariable String key){
		Student student = studentJdbc.getStudent(key);
		return student;
	}
}
