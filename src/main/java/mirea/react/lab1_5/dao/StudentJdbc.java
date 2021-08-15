package mirea.react.lab1_5.dao;

import java.sql.ResultSet;
import mirea.react.lab1_5.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentJdbc {

	private final JdbcTemplate jdbcTemplate;

	public StudentJdbc(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Student getStudent(int id){
		return jdbcTemplate.queryForObject("SELECT * FROM students WHERE id = ?", Student.class, id);
	}

	public Student getStudent(String name){
		return jdbcTemplate.queryForObject("SELECT * FROMS students WHERE name = ?", Student.class, name);
	}



}
