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
	
	public String selectProdName(String id) {
		String prodName = jdbcTemplate.queryForObject(
				"SELECT p.prodName\r\n"
				+ "    FROM product p \r\n"
				+ "    JOIN process_plan pp ON p.prodNo = pp.prodNo\r\n"
				+ "    WHERE p.prodNO = ?", String.class, id);
		return prodName;
	}
	
	public String selectGood_prod(String id) {
		System.out.println("[ProcessDao(if)]procid : " + id);
		if(id.equals("KBD001")) { // 매개변수와 비교시에는 .equals() 사용
			id = "PKB01";
			System.out.println("[IF문 내부 id] : " + id);
		}
	
		System.out.println("[ProcessDao]procid : " + id);
		String goodProd = jdbcTemplate.queryForObject(
				"SELECT COUNT(*) AS good_count\r\n"
				+ "    FROM result_prod\r\n"
				+ "    WHERE status = 0 \r\n"
				+ "    AND processID = ?", String.class, id);
		return goodProd;
	}
	
	public String selectBad_prod(String id) {
		if(id.equals("KBD001")) {
			id = "PKB01";
		}
		String badProd = jdbcTemplate.queryForObject(
				"SELECT COUNT(*) AS good_count\r\n"
				+ "    FROM result_prod\r\n"
				+ "    WHERE status = 1 \r\n"
				+ "    AND processID = ?", String.class, id);
		return badProd;
	}
	
	public String selectIssue_count(String id) {
		if(id.equals("KBD001")) {
			id = "KBPL01";
		}
		String issueCount = jdbcTemplate.queryForObject(
				"SELECT COUNT(*) AS issue_count\r\n"
				+ "        FROM process_issue\r\n"
				+ "        WHERE planID = ?", String.class, id);
		return issueCount;
	}
	

	

	/*
	public List<ProcessBean> selectById(String id) {
		List<ProcessBean> results = jdbcTemplate.query("select * from single_value where prod = ?", 
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
			}, id);
	return results;
	}
	
	public List<ProcessBean> selectByIds(String id1, String id2) {
		List<ProcessBean> results = jdbcTemplate.query("select * from single_value where prod = ? and abc=?", 
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
			}, id1, id2);
	return results;
	}*/
	/*
	// List<> 쿼리를 통한 데이터 가져오기(싱글벨류)
	public List<ProcessBean> selectAll() {
		List<ProcessBean> results = jdbcTemplate.query("select * from single_value",
				new RowMapper<ProcessBean>() {
					@Override
					public ProcessBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						ProcessBean process = new ProcessBean();
								process.setProdName(rs.getString("prodName"));
								process.setGood_count(rs.getString("good_count"));
								process.setBad_count(rs.getString("bad_count"));
								process.setIssue_count(rs.getString("issue_count"));
								return process;
					}
			});
	return results;
	}*/
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
				"SELECT percent FROM process_percent", String.class);
		return process_gauge;
	}
	// 공정 진행률(게이지 차트) 1건
	/*
	public String selectGauge(String id) {
		if(id == "KBD001") {
			id = "KBPL01";
		}
		String process_gauge = jdbcTemplate.queryForObject(
				"SELECT TRUNC(A.Qty/B.prodQty * 100,2) AS 공정진행률\r\n"
				+ "    FROM (SELECT COUNT(*) AS Qty\r\n"
				+ "        FROM result_prod R, process P, process_plan PP \r\n"
				+ "        WHERE R.processID = P.processID \r\n"
				+ "        AND P.planID = PP.planID\r\n"
				+ "        AND R.status = 0\r\n"
				+ "        AND PP.planID = ?) A,\r\n"
				+ "        (SELECT prodQty \r\n"
				+ "        FROM process_plan\r\n"
				+ "        WHERE planID = ?)B", String.class, id,id);
		return process_gauge;
	}*/
	
	//양품률/불량률
	public List<ProcessBean> select_rate() {
		List<ProcessBean> results = jdbcTemplate.query("SELECT * FROM prod_rate",
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
	/*
	// 양품률/불량률
	public List<ProcessBean> select_rate(String id) {
		if(id =="KBD001") {
			id = "PKB01";
		}
		List<ProcessBean> results = jdbcTemplate.query("SELECT TRUNC(A.Qty / C.t_Qty * 100) AS goodprod_rate,\r\n"
				+ "    TRUNC(B.Qty / C.t_Qty * 100) AS badprod_rate \r\n"
				+ "    FROM (SELECT COUNT(*) AS Qty\r\n"
				+ "        FROM result_prod\r\n"
				+ "        WHERE status = 0 \r\n"
				+ "        AND processID = ?) A,\r\n"
				+ "        (SELECT COUNT(*) AS Qty\r\n"
				+ "        FROM result_prod\r\n"
				+ "        WHERE status = 1 \r\n"
				+ "        AND processID = ?) B,\r\n"
				+ "        (SELECT COUNT(*) AS t_Qty\r\n"
				+ "        FROM result_prod\r\n"
				+ "        WHERE processID = ?)C",
				new RowMapper<ProcessBean>() {
					@Override
					public ProcessBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						ProcessBean process = new ProcessBean(
								rs.getString("goodprod_rate"),
								rs.getString("badprod_rate"));
						return process;				
					}
			},id,id,id);
	return results;
	}*/
	
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
