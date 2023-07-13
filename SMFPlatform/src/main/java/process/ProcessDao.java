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
	
	// List<> 쿼리를 통한 데이터 가져오기(싱글벨류)
	public List<ProcessBean> selectAll() {
		List<ProcessBean> results = jdbcTemplate.query("select * from single_value",
				new RowMapper<ProcessBean>() {
					@Override
					public ProcessBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						ProcessBean process = new ProcessBean(
								rs.getString("prodName"),
								rs.getString("good_count"),
								rs.getString("bad_count"),
								rs.getString("issue_count"));
						return process;				
					}
			});
	return results;
	}
	// 이슈 내용 가져오기
	public List<ProcessBean> selectIssueAll() {
		List<ProcessBean> results = jdbcTemplate.query("select * from issues",
				new RowMapper<ProcessBean>() {
					@Override
					public ProcessBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						ProcessBean process = new ProcessBean();
								process.setIssueNo(rs.getString("issueNo"));
								process.setIssueInfo(rs.getString("issueInfo"));
								process.setTimeStamp(rs.getString("timestamp"));
						return process;				
					}
			});
	return results;
	}
	
	// 공정 진행률(게이지 차트) 1건
	public String selectGauge() {
		String process_gauge = jdbcTemplate.queryForObject(
				"select percent from process_percent", String.class);
		return process_gauge;
	}
	
	// 양품률/불량률
	// 여러개의 행 중에서 첫 번째 행을 가져올 때 사용
	public ProcessBean select_rate() {
		List<ProcessBean> results = jdbcTemplate.query("select * from prod_rate",
				new RowMapper<ProcessBean>() {
					@Override
					public ProcessBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						ProcessBean process = new ProcessBean(
								rs.getString("goodprod_rate"),
								rs.getString("badprod_rate"));
						return process;				
					}
			});
	return (results != null) ? results.get(0) : null;
	}
	/*
	// 양품률/불량률
	public List<ProcessBean> select_rate_one() {
		List<ProcessBean> results = jdbcTemplate.query("select * from prod_rate",
				new RowMapper<ProcessBean>() {
					@Override
					public ProcessBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						ProcessBean process = new ProcessBean(
								rs.getString("goodprod_rate"),
								rs.getString("badprod_rate"));
						return process;				
					}
			});
	return results;
	}
	*/
	public String selectleadtime() {
		String process_leadtime = jdbcTemplate.queryForObject(
				"select * from leadtime", String.class);
		return process_leadtime;
	}
	/*
	public String selectcycletime() {
		String process_cycletime = jdbcTemplate.queryForObject(
				"select * from cycletime", String.class);
		return process_cycletime;
	}
	*/
	public List<ProcessBean> select_cycletime() {
		List<ProcessBean> results = jdbcTemplate.query("select * from cycletime_1",
				new RowMapper<ProcessBean>() {
					@Override
					public ProcessBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						ProcessBean process = new ProcessBean();
						process.setCycletime(rs.getString("cycletime"));
						return process;				
					}
			});
	return results;
	}
	
	public List<ProcessBean> select_material() {
		List<ProcessBean> results = jdbcTemplate.query("select * from result_material",
				new RowMapper<ProcessBean>() {
					@Override
					public ProcessBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						// DEFAULT 생성자 이용
						ProcessBean process = new ProcessBean(); 
						process.setMaterialname(rs.getString("matername"));
						process.setMaterialqty(rs.getString("QTY"));
						return process;				
					}
			});
	return results;
	}
	
	public int count() {
		Integer count = jdbcTemplate.queryForObject(
				"select count(*) from cycletime_1", Integer.class);
		return count;
	}

}
