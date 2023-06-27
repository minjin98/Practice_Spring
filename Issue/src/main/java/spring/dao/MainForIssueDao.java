package spring.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainForIssueDao {
	private static IssueDao IssueDao;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		IssueDao = ctx.getBean(IssueDao.class);

		selectAll();

		ctx.close();
	}

	private static void selectAll() {
		System.out.println("----- selectAll");
		int total = IssueDao.count();
		System.out.println("전체 데이터: " + total);
		List<Issue> Issues = IssueDao.selectAll();
		for (Issue m : Issues) {
			System.out.println(m.getIssueNo() + ":" + m.getIssueName() + ":" + m.getIssueInfo() + ":" +m.getTimestamp());
		}
	}

	
}
