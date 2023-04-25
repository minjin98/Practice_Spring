package main;

import java.time.LocalDateTime;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.DbConfig;
// import config.DbQueryConfig;
import config.DbQueryConfigTR;
// import dbquery.DbQuery;
import dbquery.DbQueryTR;
import spring.dao.Member;

public class MainUsingDbQueryTR {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class, DbQueryConfigTR.class);

		DbQueryTR tr = ctx.getBean(DbQueryTR.class);
		int count = tr.count();
		System.out.println("멤버 수 : " + count + "건");
		
		tr.tranactionOne(new Member("tr@abc.co.kr", "1234", "TR", LocalDateTime.now()));
		
		count = tr.count();
		System.out.println("멤버 수 : " + count + "건");
		
		ctx.close();
	}
}
