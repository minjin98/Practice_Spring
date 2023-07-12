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
		//selectAll2();
		// selectAll2()와 동일
		String process_gauge = processDao.selectGauge();
		System.out.println(process_gauge);
		System.out.println("-----------------------------");
		
		//selectAll3();
		// selectAll3()와 동일
		ProcessBean process = processDao.select_rate();
		System.out.println(process.getGoodprod_rate());
		System.out.println(process.getBadprod_rate());
		System.out.println("-----------------------------");
		
		// leadtime
		String process_leadtime = processDao.selectleadtime();
		System.out.println(process_leadtime);
		System.out.println("-----------------------------");
		
		// cycletime
		int count = processDao.count();
		System.out.println(count);
		System.out.println("-----------------------------");
		List<ProcessBean> cycletime = processDao.select_cycletime();
		System.out.println("리스트 사이즈 구하기 : " +cycletime.size());
		for(ProcessBean p : cycletime) {
			System.out.println(p.getCycletime());
		}
		
		System.out.println("-----------------------------");
		List<ProcessBean> material = processDao.select_material();
		System.out.println("material 소비량");
		for(ProcessBean p : material) {
			System.out.println(p.getMaterialname());
		}
		for(ProcessBean p : material) {
			System.out.println(p.getMaterialqty());
		}
		
		/*	
		System.out.println("-----------------------------");
		ProcessBean process_time = processDao.select_time();
		System.out.println(process_time.getAv_cycle());
		System.out.println(process_time.getLeadtime());
		*/
	
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
	/*
	private static void selectAll2() { // 게이지차트
		System.out.println("----- selectAll2");
		String process_gauge = processDao.selectGauge();
		System.out.println(process_gauge);
		System.out.println("-----------------------------");
	}*/
	
	/*
	private static void selectAll3() { // 양품률/불량률
		System.out.println("----- selectAll3");
		List<ProcessBean> process = processDao.select_rate();
		for (ProcessBean k : process) {
			System.out.println(k.getGoodprod_rate() + "/" + k.getBadprod_rate());
		}
		System.out.println("-----------------------------");
	}*/
	
}

