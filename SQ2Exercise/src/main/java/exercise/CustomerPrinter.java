package exercise;

import exercise.dao.Customer;

public class CustomerPrinter {

	public void print(Customer member) {
		System.out.printf(
				"{회원 정보}: 아이디=%d, 이름=%s, 전화번호=%s, 등록일=%tF\n", 
				member.getId(), 
				member.getName(), 
				member.getTel(),
				member.getRegisterDateTime());
	}

}
