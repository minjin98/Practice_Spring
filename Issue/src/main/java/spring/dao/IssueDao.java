package spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class IssueDao {

	private JdbcTemplate jdbcTemplate;

	public IssueDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println("[IssueDao]" + this.toString());
	}

	public List<Issue> selectAll() {
		List<Issue> results = jdbcTemplate.query("select * from VIEW_ISSUE",
				new RowMapper<Issue>() {
					@Override
					public Issue mapRow(ResultSet rs, int rowNum) throws SQLException {
						Issue member = new Issue(
								rs.getString("issueNo"),
								rs.getString("issueName"),
								rs.getString("issueInfo"),
								rs.getTimestamp("timestamp").toLocalDateTime());
						return member;				
					}
			}
				);
		return results;
	}


	public int count() {
		Integer count = jdbcTemplate.queryForObject(
				"select count(*) from VIEW_ISSUE", Integer.class);
		return count;
	}

}
