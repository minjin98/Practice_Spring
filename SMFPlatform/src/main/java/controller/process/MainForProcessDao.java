package controller.process;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForProcessDao {
	private static RegisterMgr registerMgr;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RegisterMgr.class);

		registerMgr = ctx.getBean(RegisterMgr.class);

		selectAll();
		//updateMember();
		//insertMember();

		//selectAll();

		ctx.close();
	}
	
	private static void selectAll() {
		System.out.println("----- selectAll");
		RegisterMgr mgr = new RegisterMgr();
		Vector<ProcessBean> lists = mgr.getRegisterList();
		for (ProcessBean bean : lists) {
			System.out.println(bean.getProdName());
			System.out.println(bean.getGood_count());
			System.out.println(bean.getBad_count());
			System.out.println(bean.getIssue_count());
		}
	}

	
}
