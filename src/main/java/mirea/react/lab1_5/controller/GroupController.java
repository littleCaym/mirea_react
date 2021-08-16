package mirea.react.lab1_5.controller;

import java.util.ArrayList;
import java.util.List;
import mirea.react.lab1_5.dao.GroupJdbc;
import mirea.react.lab1_5.model.Group;
import org.h2.util.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

	private final GroupJdbc groupJdbc;

	public GroupController(GroupJdbc groupJdbc){
		this.groupJdbc = groupJdbc;
	}

	@GetMapping("/group/find/{key}")
	public Group getGroup(@PathVariable String key){
		Group group = groupJdbc.getGroup(key);
		return group;
	}

	@GetMapping("/group/all")
	public List<Group> getListOfGroups(){
		List<Group> groupList = new ArrayList<>();
		groupList = groupJdbc.getListOfGroups();
		return groupList;
	}

	@PostMapping("/group/create")
	public String createGroup(@RequestBody Group group){
		String msg = groupJdbc.createGroup(group);
		return msg;
	}

	@PutMapping("/group/update/{key}")
	public String updateGroup(@PathVariable String key, @RequestBody Group group){
		String msg = groupJdbc.updateGroup(group, key);
		return msg;
	}

	@DeleteMapping("/group/delete/{key}")
	public String deleteGroup(@PathVariable String key){
		String msg = groupJdbc.deleteGroup(key);
		return msg;
	}
}
