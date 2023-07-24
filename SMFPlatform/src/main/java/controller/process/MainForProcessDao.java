package controller.process;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import config.db.OracleDbConfig;



public class MainForProcessDao {
	private static ProcessDao processDao;

	public static void main(String[] args) {
		System.out.println("main문 시작");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(OracleDbConfig.class); // 생성된 빈이 있는 클래스와 연결
		System.out.println("생성된 빈이 있는 클래스 연결");
		processDao = ctx.getBean(ProcessDao.class); // 객체에 위의 정의된 빈 사용
		System.out.println("빈 연결 성공");
		//selectAll();
		
		/*
		String prodName = processDao.selectProdName("KBD001");
		System.out.println(prodName);
		*/
		/*
		String prodNo1 = processDao.selectOneLine("3");
		System.out.println(prodNo1);
		String prodNo2 = processDao.selectTwoLine("2");
		System.out.println(prodNo2);
		String prodNo3 = processDao.selectThreeLine("3");
		System.out.println(prodNo3);
		*/
	
		/*
		List<ProcessBean>process_gauge = processDao.selectGauge("KBD001");
		for(ProcessBean p : process_gauge) {
			System.out.println(p.getProcess_gauge());
		}
		
		List<ProcessBean>process_rate = processDao.select_rate("KBD001");
		for(ProcessBean p : process_rate) {
			System.out.println(p.getGoodprod_rate()+'/' + p.getBadprod_rate());
		}
		
		String leadtime = processDao.selectleadtime("KBD001");
		System.out.println(leadtime);
		*/
		/*
		List<ProcessBean>cycletime = processDao.select_cycletime("KBD001");
		for(ProcessBean p : cycletime) {
			System.out.println(p.getCycletime());
		}*/
		/*
		List<ProcessBean>material = processDao.select_material("KBD001");
		
		for(ProcessBean p : material) {
			System.out.println(p.getMaterialname());
		}
		for(ProcessBean p : material) {
			System.out.println(p.getMaterialqty());
		}
		*/
		/*
		List<ProcessBean> order = processDao.select_plan();
		System.out.println("select_plan main문에서 실행");
		for(ProcessBean p : order) {
			System.out.println(p.getNum()+'/'+ p.getProdNo()+'/' + p.getStartDate()+'/' + p.getEndDate()+'/' + p.getName());
		}
		*/
		
		/*
		String goodProd = processDao.selectGood_prod("KBD001");
		System.out.println(goodProd);
		
		
		String badProd = processDao.selectBad_prod("KBD001");
		System.out.println(badProd);
		
		String issueCount = processDao.selectIssue_count("KBD001");
		System.out.println(issueCount);
		
		String gauge = processDao.selectGauge("KBD001");
		System.out.println(gauge);
		
		List<ProcessBean> goodbad = processDao.select_rate("KBD001");
		for(ProcessBean p : goodbad) {
			System.out.println(p.getGoodprod_rate() +"/" + p.getBadprod_rate());
		}*/
		/*
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
		
		System.out.println("-----------------------------");
		List<ProcessBean>issues = processDao.selectIssueAll();
		System.out.println("이슈 정보 출력");
		for(ProcessBean p : issues) {
			System.out.println(p.getIssueNo() +'/'+ p.getIssueInfo()+'/' + p.getTimeStamp());
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
	/*
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

