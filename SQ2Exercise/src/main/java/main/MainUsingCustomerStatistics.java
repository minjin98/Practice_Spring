package main;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import config.CustomerStatisticsConfig;
import exercise.dao.Customer;
import exercise.dao.CustomerStt;
import statistics.CustomerStatisticsService;

public class MainUsingCustomerStatistics {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class, CustomerStatisticsConfig.class);

		CustomerStatisticsService service = ctx.getBean(CustomerStatisticsService.class);

		customerGradeLists(service);
		customerSttLists(service);

		ctx.close();
	}
	public static void customerGradeLists(CustomerStatisticsService service) {
		System.out.println("[고객정보] 등급포함");
		
		List<Customer> lists = service.customerGradeLists();
		
		for(Customer customer : lists) {
			System.out.println(customer);
		}
	}
	
	public static void customerSttLists(CustomerStatisticsService service) {
		System.out.println("[고객정보] 등급포함");
		
		List<CustomerStt> lists = service.customerSttLists();
		
		for(CustomerStt customerstt : lists) {
			System.out.println(customerstt);
		}
	}
	
}
