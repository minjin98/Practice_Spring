package dbquery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import exercise.dao.PostCode;

public class PostCodeQuery {
	private DataSource dataSource;

	public PostCodeQuery(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		}
		catch(SQLException e) {
			System.out.println("[PostCodeQuery] getConnection: " + e.getMessage());
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
			System.out.println("[PostCodeQuery] closeConnection:" + e.getMessage());
		}
	}
	
	public void insert(Connection conn, PostCode postcode) throws SQLException {
		String sql = String.format("INSERT INTO postcode (postcd,area) VALUES (?, ?)");
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, postcode.getPostcd());
			stmt.setString(2, postcode.getArea());
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
					ResultSet rs = stmt.executeQuery("select count(*) from postcode")) {
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
