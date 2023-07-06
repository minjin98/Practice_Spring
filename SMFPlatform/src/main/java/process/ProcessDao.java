package process;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.apache.tomcat.jdbc.pool.DataSource;

public class ProcessDao {
	
	private JdbcTemplate jdbcTemplate;
	
	// DB 연동
	public ProcessDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println("[ProcessDao 실행]");
	}
	
	// List<> 쿼리를 통한 데이터 가져오기
	public List<Process> selectAll() {
		List<Process> results = jdbcTemplate.query("select * from single_value",
				new RowMapper<Process>() {
					@Override
					public Process mapRow(ResultSet rs, int rowNum) throws SQLException {
						Process process = new Process(
								rs.getString("prodName"),
								rs.getString("good_count"),
								rs.getString("bad_count"),
								rs.getString("issue_count"));
						return process;				
					}
			});
	return results;
	}
	
	public int count() {
		Integer count = jdbcTemplate.queryForObject(
				"select count(*) from single_value", Integer.class);
		return count;
	}

}
