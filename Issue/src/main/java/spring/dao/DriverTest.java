package spring.dao;

import java.sql.*;

public class DriverTest {
	final static private String _driver = "oracle.jdbc.driver.OracleDriver";
	final static private String _url = "jdbc:oracle:thin:@localhost:1521:xe";
	final static private String _user = "HELLOUSER";
	final static private String _password = "HELLOUSER";
	    
	public static void main(String args[]){
		Connection con;

		try {
			Class.forName(_driver).newInstance();
			con=DriverManager.getConnection(_url, _user, _password);
			System.out.println("Success");
		}
		catch(SQLException ex){ System.out.println("SQLException" + ex);}
		catch(Exception ex){ System.out.println("Exception:" + ex);}
	}
}