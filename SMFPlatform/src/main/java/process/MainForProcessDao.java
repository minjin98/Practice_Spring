package process;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainForProcessDao {
	private static ProcessDao processDao;

	public static void main(String[] args) {
		System.out.println("main문 시작");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(OracleDbConfig.class); // 생성된 빈이 있는 클래스와 연결
		System.out.println("생성된 빈이 있는 클래스 연결");
		processDao = ctx.getBean(ProcessDao.class); // 객체에 위의 정의된 빈 사용

		selectAll();
		selectAll2();
		selectAll3();
		
		//updateMember();
		//insertMember();

		//selectAll();

		ctx.close();
	}
	
	private static void selectAll() {
		System.out.println("----- selectAll");
		List<ProcessBean> process = processDao.selectAll();
		for (ProcessBean p : process) {
			System.out.println(p.getProdName() + ":" + p.getGood_count() + ":" + p.getBad_count()+ ":" + p.getIssue_count());
		}
		System.out.println("-----------------------------");
	}
	
	private static void selectAll2() {
		System.out.println("----- selectAll2");
		String process_gauge = processDao.selectGauge();
		System.out.println(process_gauge);
		System.out.println("-----------------------------");
	}
	
	private static void selectAll3() {
		System.out.println("----- selectAll3");
		List<ProcessBean> process = processDao.select_rate();
		for (ProcessBean k : process) {
			System.out.println(k.getGoodprod_rate() + "/" + k.getBadprod_rate());
		}
		System.out.println("-----------------------------");
	}
	
}

