package register.oracle;

import java.util.Vector;
import register.beans.RegisterBean;

public class RegisterMgrPoolTest {

	public static void main(String[] args) {
		RegisterMgrPool mgr = new RegisterMgrPool();
		
		Vector<RegisterBean> lists = mgr.getRegisterList();
		
		System.out.println("[RegisterMgrPool List]");
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
