package com.iihtibm.registration.repository;

import com.iihtibm.registration.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserJdbcRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setRole(rs.getString("role"));
			return user;
		}
	}

	public List<User> findAll() {
		return jdbcTemplate.query("select * from user", new UserRowMapper());
	}

	public User findById(long id) {
		return jdbcTemplate.queryForObject("select * from user where id=?", new Object[] { id },
				new BeanPropertyRowMapper<User>(User.class));
	}

	public User findByEmail(String email) {
		return jdbcTemplate.queryForObject("select * from user where email=?", new Object[] { email },
				new BeanPropertyRowMapper<User>(User.class));
	}

	public int deleteById(long id) {
		return jdbcTemplate.update("delete from user where id=?", new Object[] { id });
	}

	public int insert(User user) {
		return jdbcTemplate.update("insert into user (id, name, email, username, password, role) " + "values(?,?,?,?,?,?)",
				user.getId(), user.getName(), user.getEmail(), user.getUsername(), user.getPassword(), user.getRole());
	}

	public int update(User user) {
		return jdbcTemplate.update("update user " + " set name = ?, email = ?, username = ?, password = ?, role = ? " + " where id = ?",
				user.getName(), user.getEmail(), user.getUsername(), user.getPassword(), user.getRole(), user.getId());
	}

}
