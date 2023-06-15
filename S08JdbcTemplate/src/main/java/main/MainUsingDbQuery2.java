package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.DbConfig;
import config.DbQueryConfig;
import config.DbQueryConfig2;
import dbquery.DbQuery;

public class MainUsingDbQuery2 {

	public static void main(String[] args) {
		System.out.println("[MainUsingDbQuery2] : DbConfig.class, DbQueryConfig2.class");
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class, DbQueryConfig2.class);

		DbQuery dbQuery = ctx.getBean(DbQuery.class);
		int count = dbQuery.count();
		System.out.println("멤버 수 : " + count + "건");
		ctx.close();
	}
}
