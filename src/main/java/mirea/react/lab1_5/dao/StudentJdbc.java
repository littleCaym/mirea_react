package mirea.react.lab1_5.dao;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mirea.react.lab1_5.model.Student;
import org.h2.util.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentJdbc {

	private final JdbcTemplate jdbcTemplate;

	public StudentJdbc(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Student getStudent(String key){
		if (StringUtils.isNumber(key)) //Если был введен id
			return jdbcTemplate.queryForObject("SELECT * FROM student WHERE id = ?", this::mapStudent, Integer.parseInt(key));
		else //если фамилия
			return jdbcTemplate.queryForObject("SELECT * FROM student WHERE surname = ?", this::mapStudent, key);
	}

	public List<Student> getListOfStudents(){
		//List<Student> studentList = new ArrayList<>();
		return jdbcTemplate.query("SELECT * FROM student", this::mapStudent);
	}

	public List<Student> getListOfStudentsByGroup(int key){
		return jdbcTemplate.query("SELECT * FROM student WHERE study_group_id = ?", this::mapStudent, key);
	}


	private Student mapStudent(ResultSet rs, int i) throws SQLException {

		Student student = new Student(
				rs.getInt("id"),
				rs.getString("surname"),
				rs.getString("name"),
				rs.getString("second_name"),
				rs.getInt("study_group_id")
		);


		return student;
	}


}
