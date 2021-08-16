package mirea.react.lab1_5.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mirea.react.lab1_5.model.Group;
import org.h2.util.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GroupJdbc {

	private final JdbcTemplate jdbcTemplate;

	public GroupJdbc(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	public Group getGroup(String key){
		if(StringUtils.isNumber(key))
			return jdbcTemplate.queryForObject("SELECT * FROM study_group WHERE id = ?", this::mapGroup, Integer.parseInt(key));
		else
			return jdbcTemplate.queryForObject("SELECT * FROM study_group WHERE name = ?", this::mapGroup, key);
	}

	public List<Group> getListOfGroups(){
		List<Group> groupList = new ArrayList<>();
		groupList = jdbcTemplate.query("SELECT * FROM study_group", this::mapGroup);
		return groupList;
	}

	public String createGroup(Group group){
		jdbcTemplate.update("INSERT INTO study_group (id, name) VALUES (?,?)", group.getId(),group.getName());
		return "New group was created!";
	}

	public String updateGroup(Group group, String key){
		if (StringUtils.isNumber(key))
			jdbcTemplate.update("UPDATE study_group SET id = ?, name = ? WHERE id = ?",
				group.getId(), group.getName(), Integer.parseInt(key)
				);
		else
			jdbcTemplate.update("UPDATE study_group SET id = ?, name = ? WHERE name = ?",
					group.getId(), group.getName(), key
			);
		return "The Group was updated!";
	}

	public String deleteGroup(String key){
		if (StringUtils.isNumber(key))
			jdbcTemplate.update("DELETE FROM study_group WHERE id = ?", Integer.parseInt(key));
		else
			jdbcTemplate.update("DELETE FROM study_group WHERE name = ?", key);
		return "The group " + key + " was successfully deleted!";
	}

	public Group mapGroup(ResultSet rs, int id) throws SQLException {
		Group group = new Group(
				rs.getInt("id"),
				rs.getString("name")
		);
		return group;
	}
}
