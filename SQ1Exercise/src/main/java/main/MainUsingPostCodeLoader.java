package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.DbConfig;
import config.PostCodeQueryConfig;
import dbquery.DbQuery;
import dbquery.PostCodeQuery;
import exercise.dao.PostCode;

public class MainUsingPostCodeLoader {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class, PostCodeQueryConfig.class);

		PostCodeQuery query = ctx.getBean(PostCodeQuery.class);
		
		int readcnt = loader(query);
		int loadcnt = query.count();
		System.out.printf("읽은건수(%d), 등록건수(%d)\n", readcnt, loadcnt); 
		ctx.close();
	}
	
	public static int loader(PostCodeQuery query) {
		int readcnt = 0;

		Connection conn = null;
		
		try(BufferedReader reader = new BufferedReader(new FileReader("postcode.csv"))) {
			
			conn = query.getConnection();
			
			while(conn != null) {
				String text = reader.readLine();
				if(text == null) { 
					break;
				}
				
				String texts[] = text.split(",");
				if(texts.length == 2) {
					PostCode post = new PostCode(texts[0], texts[1]);
					query.insert(conn, post);					
				}
				
				System.out.printf("[%d] %s \n", ++readcnt, text);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null) {
				query.closeConnection(conn);
			}
		}
		
		return readcnt;
	}
	
}
