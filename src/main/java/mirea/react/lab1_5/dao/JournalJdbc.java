package mirea.react.lab1_5.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mirea.react.lab1_5.model.Journal;
import mirea.react.lab1_5.model.Mark;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class JournalJdbc {

	private final JdbcTemplate jdbcTemplate;

	public JournalJdbc(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Journal> getListOfNotes(){
		List<Journal> journalList = new ArrayList<>();
		journalList = jdbcTemplate.query("SELECT * FROM journal", this::mapJournal);
		return journalList;
	}

	public Journal getJournalByStudent(int key){
		Journal journal = jdbcTemplate.queryForObject("SELECT * FROM journal WHERE student_id = ?", this::mapJournal, key);
		return journal;
	}

	public Journal getJournalByGroup(int key){
		Journal journal = jdbcTemplate.queryForObject(
				"SELECT * FROM journal JOIN student ON journal.student_id=student.id WHERE student.study_group_id = ?",
				this::mapJournal, key
		);
		return journal;
	}

	public String updateJournal(Mark mark, int key){
		jdbcTemplate.update(
				"UPDATE (SELECT * FROM journal LEFT JOIN mark ON journal.mark_id = mark.id) "
						+ "SET name = ?, value = ?) ",
				mark.getName(), mark.getValue()
		);
		return "The note in journal was successfully updated!";
	}

	private Journal mapJournal(ResultSet rs, int id) throws SQLException {
		Journal journal = new Journal(
				rs.getInt("id"),
				rs.getInt("student_id"),
				rs.getInt("study_plan_id"),
				rs.getByte("in_time"),
				rs.getInt("count"),
				rs.getInt("mark_id")
		);
		return journal;
	}
}
