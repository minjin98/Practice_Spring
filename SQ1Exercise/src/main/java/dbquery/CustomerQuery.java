package dbquery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import exercise.dao.Customer;

public class CustomerQuery {
	private DataSource dataSource;

	public CustomerQuery(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		}
		catch(SQLException e) {
			System.out.println("[CustomerQuery] getConnection: " + e.getMessage());
		}
		
		return null;
	}
	
	public void closeConnection(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		}
		catch(SQLException e) {
			System.out.println("[CostomerQuery] closeConnection:" + e.getMessage());
		}
	}
	
	public void insert(Connection conn, Customer customer) throws SQLException {
		String sql = String.format("INSERT INTO CUSTOMER (id, name, tel, postcd, area, point, regdate) VALUES (?, ?, ?, ?, ?, ?, sysdate)");
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customer.getId());
			stmt.setString(2, customer.getName());
			stmt.setString(3, customer.getTel());
			stmt.setString(4, customer.getPostcd());
			stmt.setString(5, customer.getArea());
			stmt.setString(6, customer.getPoint());
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			if(e.getErrorCode() != 1) { // 메인키 중복
				System.out.println("[insert] Exception:" + e.getMessage());
				throw new SQLException(e);
			}
		}
		finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
			}
			catch(Exception se) {
				se.printStackTrace();
			}
		}
	}
	

	public int count() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			try (Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select count(*) from CUSTOMER")) {
				rs.next();
				return rs.getInt(1);
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
	}	
}