package config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dbquery.DbQuery;

@Configuration
public class DbQueryConfig2 {
/*
	@Autowired
	private javax.sql.DataSource dataSource;
*/
	@Bean
	public DbQuery dbQuery(DataSource dataSource) {
		return new DbQuery(dataSource);
	}
}
