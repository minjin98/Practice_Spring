/*
 * 전통적인 JDBC 처리 모듈
 * DataSource는 tomcat의 커넥션 풀을 사용
 */
package dbquery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import exercise.dao.Member;
import exercise.exceptions.MemberNotFoundException;

public class DbQueryTR {
	private DataSource dataSource;

	public DbQueryTR(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int count() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			System.out.println("[DbQuery] autocommit=" + conn.getAutoCommit());
			
			try (Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select count(*) from MEMBER")) {
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
	
	public void tranactionOne(Member member) {
		Connection conn = null;
		
		try {
			System.out.println("[트랜잭션] 시작");

			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			System.out.println("[DbQuery] autocommit=" + conn.getAutoCommit());
			
			System.out.println("[트랜잭션] 입력");
			insert(conn, member);
			// insert2(conn, member);
			
			System.out.println("[트랜잭션] 수정");
			update(conn, member);
			
			System.out.println("[트랜잭션] 삭제");
			
			// 예외발생
			// member.setEmail("TR@abc.co.kr");
			
			// 정상처리
			member.setEmail("tr@abc.co.kr");
			delete(conn, member);
			
			conn.commit();	// 트랜잭션 완료
			System.out.println("[트랜잭션] 커밋완료");
		} 
		catch (SQLException | MemberNotFoundException e) {
			System.out.println("[트랜잭션] 예외발생 시작 ");
			System.out.println("[트랜잭션] " + e.getMessage());

			try {
				if(conn != null) {
					System.out.println("[트랜잭션] 취소");
					conn.rollback();	// 트랜잭션 취소
				}
			}
			catch(SQLException ce) {
				System.out.println("[예외발생] connection close failed. " + ce.getMessage());
			}
			System.out.println("[트랜잭션] 예외발생 RuntimeException()");
			throw new RuntimeException(e);
		} 
		finally {
			System.out.println("[트랜잭션] finally 시작");
			if (conn != null) {
				try {
					System.out.println("[트랜잭션] finally, conn.close()");
					conn.close();
				} 
				catch (SQLException e) {
				}
			}
		}
		
		System.out.println("[작업종료]");
	}
	
	public void delete(Connection conn, Member member) throws SQLException, MemberNotFoundException {
		String sql = String.format("DELETE FROM member WHERE email = '%s' ", member.getEmail());
		System.out.println("[delete] sql=" + sql);
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			int delcnt = stmt.executeUpdate(sql);
			System.out.println("[delete] delete count=" + delcnt);
			if(delcnt <= 0) {
				throw new MemberNotFoundException("삭제할 데이터가 없습니다.");
			}
		}
		catch(SQLException e) {
			throw new SQLException(e);
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
	
	public void update(Connection conn, Member member) throws SQLException {
		String sql = String.format("UPDATE member SET name=?, password=? WHERE email=?");
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getEmail());
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			throw new SQLException();
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
	
	public void insert(Connection conn, Member member) throws SQLException {
		String sql = String.format("INSERT INTO member (id,name,email,password,regdate) VALUES (member_id_seq.nextval, ?, ?, ?, sysdate)");
		PreparedStatement stmt = null;
		
		System.out.println("[insert] member: " + member);
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getEmail());
			stmt.setString(3, member.getPassword());
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("[insert] Exception:" + e.getMessage());
			throw new SQLException(e);
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

	public void insert2(Connection conn, Member member) throws SQLException {
		String sql = String.format("INSERT INTO member VALUES (member_id_seq.nextval, '%s', '%s', '%s', sysdate)",
				member.getName(), member.getEmail(), member.getPassword());
		
		Statement stmt = null;
		
		System.out.println("[insert2] member: " + member);
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(SQLException e) {
			System.out.println("[insert2] Exception:" + e.getMessage());
			throw new SQLException(e);
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

	
}
