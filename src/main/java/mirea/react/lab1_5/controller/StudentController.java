package mirea.react.lab1_5.controller;

import java.util.ArrayList;
import java.util.List;
import mirea.react.lab1_5.dao.StudentJdbc;
import mirea.react.lab1_5.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//TODO: необходимо добавить обработку неверно введенных или несуществующих запросов

@RestController
public class StudentController {

	private final StudentJdbc studentJdbc;

	public StudentController(StudentJdbc studentJdbc){this.studentJdbc = studentJdbc;}


	//Выдает либо по фамилии, либо по id
	@GetMapping("/student/find/{key}")
	public Student getStudent(@PathVariable String key){
		Student student = studentJdbc.getStudent(key);
		return student;
	}

	@GetMapping("/student/all")
	public List<Student> getListOfStudents(){
		List<Student> studentList = new ArrayList<>();
		studentList = studentJdbc.getListOfStudents();
		return studentList;
	}

	//Поиск студентов по группе
	@GetMapping("/student/group/{key}")
	public List<Student> getListOfStudentsByGroup(@PathVariable int key){
		List<Student> studentList = studentJdbc.getListOfStudentsByGroup(key);
		return studentList;
	}

	//Создание студентов
	@PostMapping("/student/create")
	public String createStudent(@RequestBody Student student){
		String msg = studentJdbc.createStudent(student);
		return msg;
	}
}
