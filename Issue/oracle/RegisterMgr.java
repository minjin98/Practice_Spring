package register.oracle;

import java.sql.Connection;
// import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import register.beans.RegisterBean;
import dbutils.oracle.OracleConnector;
 
 public class RegisterMgr {
	/*
 	private final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
 	private final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
 	private final String USER = "HELLOUSER";
 	private final String PASS = "HELLOUSER";
 	*/
 	public RegisterMgr() {
 		/*
 		try {
	 		 Class.forName(JDBC_DRIVER);
	 	}
	 	catch(Exception e){
	 		System.out.println("Error : JDBC 드라이버 로딩 실패");
	 	}
	 	*/
     }
	
	public void delete(String id) {
		String sql = String.format("DELETE FROM register WHERE id = '%s'", id);
		
		Connection conn = null;
		Statement  stmt = null;
		
		System.out.println("[Hello Table DELETE]");
		
		try {
			conn = OracleConnector.getConnection();
			stmt = conn.createStatement();
			
			int success = stmt.executeUpdate(sql);
			if(success > 0) { // 처리한 갯수를 리턴
				System.out.printf("DELETE: succeed(%d)\n", success);
			}
			else {
				System.out.printf("DELETE: failed(%d)\n", success);
			}
		}
		catch(SQLException e) {
			System.out.println("DELETE: SQLException: " + e.toString());
		}
		finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				
			}
			catch(Exception e) {
				System.out.println("DELETE:finally:Exception: " + e.toString());
			}
			
			OracleConnector.closeConnection();
		}
	}
	
	public void update(RegisterBean bean) {
		String sql = String.format("UPDATE register SET pwd='%s'," +
				"name='%s', num1='%s', num2='%s', email='%s'," +
				"phone='%s', zipcode='%s', address='%s', job='%s' " +
				"WHERE id='%s'",
				bean.getPwd(), bean.getName(), bean.getNum1(), bean.getNum2(),
				bean.getEmail(), bean.getPhone(), bean.getZipcode(), bean.getAddress(), 
				bean.getJob(), bean.getId());
		
		System.out.println("[update] " + sql);
		
		Connection conn = null;
		Statement  stmt = null;
		
		System.out.println("[Hello Table UPDATE]");
		
		try {
			conn = OracleConnector.getConnection();
			stmt = conn.createStatement();
			int success = stmt.executeUpdate(sql);
			if(success > 0) { // 처리한 갯수를 리턴
				System.out.printf("UPDATE: succeed(%d)\n", success);
			}
			else {
				System.out.printf("UPDATE: failed(%d)\n", success);
			}
		}
		catch(SQLException e) {
			System.out.println("UPDATE: SQLException: " + e.toString());
		}
		finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				
			}
			catch(Exception e) {
				System.out.println("UPDATE:finally:Exception: " + e.toString());
			}
			
			OracleConnector.closeConnection();
		}
	}

	public void insert(RegisterBean bean) {
		String sql = String.format("INSERT INTO register VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
				bean.getId(), bean.getPwd(), bean.getName(), bean.getNum1(), bean.getNum2(),
				bean.getEmail(), bean.getPhone(), bean.getZipcode(), bean.getAddress(), 
				bean.getJob());

		System.out.println("[insert] sql : " + sql);
		
		Connection conn = null;
		Statement  stmt = null;
		
		System.out.println("[Hello Table Insert]");
		
		try {
			conn = OracleConnector.getConnection();
			stmt = conn.createStatement();
			int success = stmt.executeUpdate(sql);
			if(success > 0) { // 처리한 갯수를 리턴
				System.out.printf("INSERT: succeed(%d)\n", success);
			}
			else {
				System.out.println("INSERT: failed!!!");
			}
		}
		catch(SQLException e) {
			System.out.println("INSERT: SQLException: " + e.toString());
		}
		finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
			}
			catch(Exception e) {
				System.out.println("INSERT: " + e.toString());
			}
			
			OracleConnector.closeConnection();
		}
	}
	 
    public Vector<RegisterBean> getRegisterList() {
    	final String strQuery = "select * from register order by name";
    	
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
       
		Vector<RegisterBean> vlist = new Vector<RegisterBean>();
       
       try {
          // conn = DriverManager.getConnection(JDBC_URL, USER, PASS);	// DBMS 접속 객체를 얻음
          conn = OracleConnector.getConnection();	// DBMS 접속 객체를 얻음
          stmt = conn.createStatement();		// 쿼리(SQL)를 실행 할 수 있는 객체를 얻음
          rs = stmt.executeQuery(strQuery);		// 쿼리(SQL)를 실행, 결과를 리턴
          
		  while (rs.next()) {	// 결과셋에 데이터가 있으면 true
             RegisterBean bean = new RegisterBean();
		 	 bean.setId (rs.getString("id"));
			 bean.setPwd (rs.getString("pwd"));
 			 bean.setName (rs.getString("name"));
 			 bean.setNum1 (rs.getString("num1"));
 			 bean.setNum2 (rs.getString("num2"));
 			 bean.setEmail (rs.getString("email"));
 			 bean.setPhone (rs.getString("phone"));
 			 bean.setZipcode (rs.getString("zipcode"));
 			 bean.setAddress (rs.getString("address"));
 			 bean.setJob (rs.getString("job"));
 			 vlist.addElement(bean);
          }
       } 
       catch (Exception ex) {
          System.out.println("Exception" + ex);
       } 
       finally {
          if(rs!=null)   try{rs.close();}  catch(SQLException e){}
		  if(stmt!=null) try{stmt.close();}catch(SQLException e){}
	      // if(conn!=null) try{conn.close();}catch(SQLException e){}
		  OracleConnector.closeConnection();
       }
       
       return vlist;
    }
 }