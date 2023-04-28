package exercise;

import java.util.Collection;

import exercise.dao.Customer;
import exercise.dao.CustomerDao;

public class CustomerListPrinter {

	private CustomerDao customerDao;
	private CustomerPrinter printer;

	public CustomerListPrinter(CustomerDao customerDao, CustomerPrinter printer) {
		this.customerDao = customerDao;
		this.printer = printer;
	}

	public void printAll() {
		Collection<Customer> members = customerDao.selectAll();
		members.forEach(m -> printer.print(m));
	}

}
