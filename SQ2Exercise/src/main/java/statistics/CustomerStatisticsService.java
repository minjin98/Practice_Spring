
package statistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.transaction.annotation.Transactional;

import exercise.dao.Customer;
import exercise.dao.CustomerDao;
import exercise.dao.CustomerStt;

public class CustomerStatisticsService {
	// private CustomerDao customerDao;
	private DataSource dataSource;

	public CustomerStatisticsService(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	// @Transactional
	// 고객 등급이 있는 고객 목록을 배열로 리턴
	public List<Customer> customerGradeLists() {
		List<Customer> lists = new ArrayList<Customer>();
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			try (Statement stmt = conn.createStatement(); 
					ResultSet rs = stmt.executeQuery("select * from customerview order by grade")) {
				
				while(rs.next()) {
					Customer customer = new Customer(
							rs.getString("id"), 
							rs.getString("name"),	
							rs.getString("tel"),
							rs.getString("postcd"), 
							rs.getString("address"), 
							rs.getLong("point"),
							rs.getString("grade"),
							rs.getTimestamp("regdate").toLocalDateTime());	
					lists.add(customer);
				}
			}
		} 
		catch (SQLException e) {
			throw new RuntimeException(e);
		} 
		finally {
			if (conn != null) {
				try {
					conn.close();
				} 
				catch (SQLException e) {
				}
			}
		}
		return lists;
	}
	// 고객 등급별 통계
	public List<CustomerStt> customerSttLists() {
		List<CustomerStt> lists = new ArrayList<CustomerStt>();
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			try (Statement stmt = conn.createStatement(); 
					ResultSet rs = stmt.executeQuery("select * from customerstt")) {
				
				while(rs.next()) {
					CustomerStt customerstt = new CustomerStt(
							rs.getString("grade"), 
							rs.getLong("cnt"),	
							rs.getFloat("avg"),
							rs.getLong("max"), 
							rs.getLong("min")); 
					lists.add(customerstt);
				}
			}
		} 
		catch (SQLException e) {
			throw new RuntimeException(e);
		} 
		finally {
			if (conn != null) {
				try {
					conn.close();
				} 
				catch (SQLException e) {
				}
			}
		}
		return lists;
	}
	

}
