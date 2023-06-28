package register.oracle;

import java.util.Vector;
import register.beans.RegisterBean;

public class RegisterMgrTest {

	public static void main(String[] args) {
		//insert();
		update();
		select();
	}
	
	static void insert() {
		RegisterMgr mgr = new RegisterMgr();
		
		RegisterBean bean = new RegisterBean();
		bean.setId("9999");
		bean.setName("비둘기");
		mgr.insert(bean);
	}
	
	static void update() {
		RegisterMgr mgr = new RegisterMgr();
		
		RegisterBean bean = new RegisterBean();
		bean.setId("9999");
		bean.setName("구미호");
		mgr.update(bean);
	}
	
	
	static void select() {
		RegisterMgr mgr = new RegisterMgr();
		
		Vector<RegisterBean> lists = mgr.getRegisterList();
		
		System.out.println("[Register List]");
		System.out.println("----------------------");
		System.out.printf("%-10s", "ID");
		System.out.printf("%s\n", "이름");
		System.out.println("----------------------");

		for(RegisterBean bean : lists) {
			System.out.printf("%-10s", bean.getId());
			System.out.printf("%s\n", bean.getName());
		}
		System.out.println("----------------------");
	}
}
