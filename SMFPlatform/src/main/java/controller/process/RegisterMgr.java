package controller.process;

 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import org.springframework.jdbc.core.RowMapper;

 
 public class RegisterMgr {
    
	 	private final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	 	private final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	 	private final String USER = "HELLOUSER";
	 	private final String PASS = "HELLOUSER";
 
 	public RegisterMgr() {
 	 try{
 	   Class.forName(JDBC_DRIVER);
 	   }catch(Exception e){
 	      System.out.println("Error : JDBC 드라이버 로딩 실패");
 	   }
     }
 
    public Vector<ProcessBean> getRegisterList() {
	   Connection conn = null;
	   Statement stmt = null;
       ResultSet rs = null;
       Vector<ProcessBean> vlist = new Vector<ProcessBean>();
       try {
          conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
          String strQuery = "select * from single_value";
          stmt = conn.createStatement();
          rs = stmt.executeQuery(strQuery);
		  while (rs.next()) {
             ProcessBean bean = new ProcessBean();
		 	 bean.setProdName (rs.getString("prodName"));
			 bean.setGood_count (rs.getString("good_count"));
 			 bean.setBad_count (rs.getString("bad_count"));
 			 bean.setIssue_count (rs.getString("issue_count"));
 			 vlist.addElement(bean);
          }
		
       } catch (Exception ex) {
          System.out.println("Exception" + ex);
       } finally {
          if(rs!=null)   try{rs.close();}  catch(SQLException e){}
		  if(stmt!=null) try{stmt.close();}catch(SQLException e){}
	      if(conn!=null) try{conn.close();}catch(SQLException e){}
       }
       return vlist;
    }
 
 }