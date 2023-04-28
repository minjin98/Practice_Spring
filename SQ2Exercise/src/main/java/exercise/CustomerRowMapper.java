package exercise;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import exercise.dao.Customer;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer member = new Customer(
				rs.getString("ID"),
				rs.getString("NAME"),
				rs.getString("TEL"),
				rs.getString("POSTCD"),
				rs.getString("ADDRESS"),
				rs.getLong("POINT"),
				rs.getTimestamp("REGDATE").toLocalDateTime());
		return member;
	}

}
