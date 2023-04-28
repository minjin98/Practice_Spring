package exercise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class CustomerDao {

	private JdbcTemplate jdbcTemplate;

	public CustomerDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Customer selectById(String id) {		// 동일한 이메일에 해당하는 한 건만 리턴
		List<Customer> results = jdbcTemplate.query(	// 다중 결과는 List로 받음
				"select * from customer where id = ?",
				new RowMapper<Customer>() {
					
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
				}, id);

		return results.isEmpty() ? null : results.get(0);
	}

	public void insert(Customer member) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement("insert into customer values (?, ?, ?, ?, ?, ?, ?)");
				
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getName());
				pstmt.setString(3, member.getTel());
				pstmt.setString(4, member.getPostcd());
				pstmt.setString(5, member.getAddress());
				pstmt.setLong(6, member.getPoint());
				pstmt.setTimestamp(7,Timestamp.valueOf(member.getRegisterDateTime()));
				
				return pstmt;
			}
		});
	}

	public void update(Customer member) {
		jdbcTemplate.update("update customer set name=?, tel=?, postcd=?, address=?, point=? where id = ?",
				member.getName(),
				member.getTel(),
				member.getPostcd(),
				member.getAddress(),
				member.getPoint(),
				member.getId());
	}
	
	public int delete(String id) {
		return jdbcTemplate.update("delete from customer where id = ?", id);
	}

	public List<Customer> selectAll() {
		List<Customer> results = jdbcTemplate.query("select * from customer",
				(ResultSet rs, int rowNum) -> {
					Customer member = new Customer(
							rs.getString("ID"),
							rs.getString("NAME"),
							rs.getString("TEL"),
							rs.getString("POSTCD"),
							rs.getString("ADDRESS"),
							rs.getLong("POINT"),
							rs.getTimestamp("REGDATE").toLocalDateTime());
					return member;
				});
		return results;
	}
	
	public List<Customer> selectAllView() {
		List<Customer> results = jdbcTemplate.query("select * from customerview",
				(ResultSet rs, int rowNum) -> {
					Customer member = new Customer(
							rs.getString("ID"),
							rs.getString("NAME"),
							rs.getString("TEL"),
							rs.getString("POSTCD"),
							rs.getString("ADDRESS"),
							rs.getLong("POINT"),
							rs.getTimestamp("REGDATE").toLocalDateTime());
					return member;
				});
		return results;
	}

	
	public int count() {
		Integer count = jdbcTemplate.queryForObject("select count(*) from customer", Integer.class);
		return count;
	}

}
