package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import config.CustomerQueryConfig;
import dbquery.CustomerQuery;
import exercise.dao.Customer;

public class MainUsingCustomerLoader {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtx.class, CustomerQueryConfig.class);

		CustomerQuery query = ctx.getBean(CustomerQuery.class);
		
		int readcnt = loader(query);
		int loadcnt = query.count();
		System.out.printf("읽은건수(%d), 등록건수(%d)\n", readcnt, loadcnt); 
		ctx.close();
	}
	
	public static int loader(CustomerQuery query) {
		int readcnt = 0;

		Connection conn = null;
		
		try(BufferedReader reader = new BufferedReader(new FileReader("customer.csv"))) {
			
			conn = query.getConnection();
			
			while(conn != null) {
				String text = reader.readLine();
				if(text == null) { 
					break;
				}
				
				String texts[] = text.split(",");
				if(texts.length == 6) {
					Long point = Long.parseLong(texts[5]);
					Customer member = new Customer(texts[0], texts[1], texts[2], texts[3], texts[4], point);
					query.insert(conn, member);					
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
