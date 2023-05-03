package spring;

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

public class MemberDao {

	private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Member selectByEmail(String email) {
		/*
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER where EMAIL = ?",
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member(
								rs.getString("EMAIL"),
								rs.getString("PASSWORD"),
								rs.getString("NAME"),
								rs.getTimestamp("REGDATE").toLocalDateTime());
						member.setId(rs.getLong("ID"));
						return member;
					}
				}, email);
				*/
		Object[] where = new Object[]{email};
		
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER where EMAIL = ?", where,
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {

						Member member = new Member(
								rs.getString("EMAIL"),
								rs.getString("PASSWORD"),
								rs.getString("NAME"),
								rs.getTimestamp("REGDATE").toLocalDateTime());
								member.setId(rs.getLong("ID"));
						/*
						Member member = new Member(
								rs.getString(2),	// email
								rs.getString(3),	// password
								rs.getString(4),	// name
								rs.getTimestamp(5).toLocalDateTime());	// regdate
						
						member.setId(rs.getLong(1));*/
						return member;
					}
				});
		
		return results.isEmpty() ? null : results.get(0);
	}

	public void insert(Member member) {
		// 테이블에서 자동 생성된 필드 값을 얻음: SEQUENCE 값
		// 입력된 MEMBER 테이블의 필드 ID값을 얻음(MEMBER.ID) 
		KeyHolder keyHolder = new GeneratedKeyHolder();	
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
				PreparedStatement pstmt = con.prepareStatement(
						"insert into MEMBER (ID, EMAIL, PASSWORD, NAME, REGDATE) " +
						"values (member_id_seq.nextval, ?, ?, ?, ?)",
						new String[] { "ID" });
				
				// 인덱스 파라미터 값 설정
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4,Timestamp.valueOf(member.getRegisterDateTime()));
				
				// 생성한 PreparedStatement 객체 리턴
				return pstmt;
			}
		}, keyHolder);
		
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}			

	public void update(Member member) {
		jdbcTemplate.update(
				"update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
				member.getName(), member.getPassword(), member.getEmail());
	}

	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("select * from MEMBER",
				(ResultSet rs, int rowNum) -> {
					/*Member member = new Member(
							rs.getString("EMAIL"),
							rs.getString("PASSWORD"),
							rs.getString("NAME"),
							rs.getTimestamp("REGDATE").toLocalDateTime());
					member.setId(rs.getLong("ID"));*/
	
					
					// 오타로 인한 에러를 방지하기 위해 Member.java에 static 변수를 선언
					Member member = new Member(
							rs.getString(Member.EMAIL),
							rs.getString(Member.PASSWORD),
							rs.getString(Member.NAME),
							rs.getTimestamp(Member.REGDATE).toLocalDateTime());
					member.setId(rs.getLong(Member.ID));
	
					return member;
				});
		return results;
	}

	public int count() {
		Integer count = jdbcTemplate.queryForObject(
				"select count(*) from MEMBER", Integer.class);
		return count;
	}

}
