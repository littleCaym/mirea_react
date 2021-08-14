package mirea.react.lab1_5.controller;

import mirea.react.lab1_5.dao.MarkJdbc;
import mirea.react.lab1_5.model.Mark;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarkController {

	private final MarkJdbc markJdbc;

	public MarkController(MarkJdbc markJdbc){
		this.markJdbc = markJdbc;
	}

	@GetMapping("/mark/{id}")
	public Mark getMark(@PathVariable int id){
		Mark mark =markJdbc.get(id);
		return mark;
	}
}
