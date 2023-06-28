package spring.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainForIssueDao {
	//ivate static IssueDao IssueDao;

	public static void main(String[] args) {
		System.out.println("MainforIssueDao");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		IssueDao issueDao= ctx.getBean(IssueDao.class);
 
		selectAll(issueDao);

		ctx.close();
	}

	private static void selectAll(IssueDao issueDao) {
		System.out.println("----- selectAll");
		int total = issueDao.count();
		System.out.println("전체 데이터: " + total);
		List<Issue> Issues = issueDao.selectAll();
		for (Issue m : Issues) {
			System.out.println(m.getIssueNo() + "-" + m.getIssueName() + "-" + m.getIssueInfo() + m.getTimestamp());
		}
	}

	
}
