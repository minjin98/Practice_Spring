package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import config.PostCodeQueryConfig;
import dbquery.PostCodeQuery;
import exercise.dao.PostCode;
import utils.StringUtils;

public class MainUsingPostCodeLoader {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtx.class, PostCodeQueryConfig.class);

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
				
				Long point1 = Long.parseLong(texts[0]);			// 문자열 -> Long
				Integer point2 = Integer.parseInt(texts[0]);	// 문자열 -> Integer
				
				
				if(texts.length == 2) {
					String area = StringUtils.rtrim(texts[1]);
					PostCode post = new PostCode(texts[0], area);
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
