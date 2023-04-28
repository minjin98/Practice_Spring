package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.CustomerQueryConfig;
import config.DbConfig;
import config.PostCodeQueryConfig;
import dbquery.CustomerQuery;
import dbquery.DbQuery;
import exercise.dao.Customer;

public class MainUsingCustomerTotal {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class, CustomerQueryConfig.class);

		CustomerQuery query = ctx.getBean(CustomerQuery.class);
		
		int readcnt = loader(query);
		int loadcnt = query.count();
		System.out.printf("등록건수(%d)\n", loadcnt); 
		ctx.close();
	}
	
	public static int loader(CustomerQuery query) {
		int readcnt = 0;

		Connection conn = null;
		
		try(BufferedReader reader = new BufferedReader(new FileReader("customercard.csv"))) {
			
			conn = query.getConnection();
			
			while(conn != null) {
				String text = reader.readLine();
				if(text == null) { 
					break;
				}
				
				String texts[] = text.split(",");
				int tax =0;
				if(texts.length == 6) {
					Customer customer = new Customer(texts[0], texts[1], texts[2], texts[3],texts[4],texts[5]);
					query.insert(conn, customer);					
				}
				
				System.out.printf("%s \n", texts[5]);
				
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
