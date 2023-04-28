package exercise;

import exercise.dao.Customer;
import exercise.dao.CustomerDao;

public class CustomerInfoPrinter {

	private CustomerDao customerDao;
	private CustomerPrinter printer;

	public void printMemberInfo(String id) {
		Customer member = customerDao.selectById(id);
		if (member == null) {
			System.out.println("데이터 없음\n");
			return;
		}
		printer.print(member);
		System.out.println();
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void setPrinter(CustomerPrinter printer) {
		this.printer = printer;
	}

}
