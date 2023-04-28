package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dbquery.CustomerQuery;
import statistics.CustomerStatisticsService;

@Configuration
public class CustomerStatisticsConfig {

	// private CustomerDao customerDao;
	@Autowired
	private javax.sql.DataSource dataSource;
	
	@Bean
	public CustomerStatisticsService customerStatisticsService() {
		return new CustomerStatisticsService(this.dataSource);
	}
}
