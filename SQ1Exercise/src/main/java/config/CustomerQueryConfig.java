package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dbquery.CustomerQuery;
import dbquery.DbQuery;

@Configuration
public class CustomerQueryConfig {

	@Autowired
	private javax.sql.DataSource dataSource;

	@Bean
	public CustomerQuery customerQuery() {
		return new CustomerQuery(this.dataSource);
	}
}
