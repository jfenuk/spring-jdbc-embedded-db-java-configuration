package com.fenuk.example.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fenuk.example.model.Employee;

@Repository
public class EmployeeJdbcRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String INSERT_QUERY = "insert into employee (name, salary) values(?, ?)";
	private static final String UPDATE_QUERY = "update employee set name= ?, salary=? where id=?";
	private static final String DELETE_QUERY = "delete from employee where id=?";
	private static final String GET_BY_ID_QUERY = "select id, name, salary from employee where id = ?";
	private static final String GET_BY_NAME_QUERY = "select id, name, salary from employee where name = ?";

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;
	}

	public int save(Employee e) {
		return jdbcTemplate.update(INSERT_QUERY, new Object[] { e.getName(), e.getSalary() });
	}

	public int update(Employee e) {

		return jdbcTemplate.update(UPDATE_QUERY, new Object[] { e.getName(), e.getSalary(), e.getId() });
	}

	public int delete(Employee e) {
		return jdbcTemplate.update(DELETE_QUERY, new Object[] { e.getId() });
	}

	public Employee getById(int id) {

		Employee e = this.jdbcTemplate.queryForObject(GET_BY_ID_QUERY, new Object[] { id }, new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

				Employee e = new Employee(rs.getInt("id"), rs.getString("name"), rs.getFloat("salary"));

				return e;
			}
		});
		return e;
	}

	public Employee getByName(String name) {

		Employee e = this.jdbcTemplate.queryForObject(GET_BY_NAME_QUERY, new Object[] { name },
				new RowMapper<Employee>() {
					public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

						Employee e = new Employee(rs.getInt("id"), rs.getString("name"), rs.getFloat("salary"));

						return e;
					}
				});
		return e;
	}
}
