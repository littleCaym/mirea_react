package mirea.react.lab1_5.controller;

import java.util.ArrayList;
import java.util.List;
import mirea.react.lab1_5.dao.JournalJdbc;
import mirea.react.lab1_5.model.Journal;
import mirea.react.lab1_5.model.Mark;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JournalController {

	private final JournalJdbc journalJdbc;

	public JournalController(JournalJdbc journalJdbc) {
		this.journalJdbc = journalJdbc;
	}

	@GetMapping("/journal/all")
	public List<Journal> getListOfNotes(){
		List<Journal> journalList = new ArrayList<>();
		journalList = journalJdbc.getListOfNotes();
		return journalList;
	}

	//Поиск студента по ключу
	@GetMapping("/journal/find/student/{key}")
	public Journal getJournalByStudent(@PathVariable int key){
		Journal journal = journalJdbc.getJournalByStudent(key);
		return journal;
	}

	//Поиск студента по группе(id)
	@GetMapping("/journal/find/group/{key}")
	public Journal getJournalByGroup(@PathVariable int key){
		Journal journal = journalJdbc.getJournalByGroup(key);
		return journal;
	}

	@PutMapping("/journal/update/{key}")
	public String updateJournal(@PathVariable int key, @RequestBody Mark mark){
		String msg = journalJdbc.updateJournal(mark, key);
		return msg;
	}
}
