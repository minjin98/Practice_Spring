package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dbquery.PostCodeQuery;

@Configuration
public class PostCodeQueryConfig {

	@Autowired
	private javax.sql.DataSource dataSource;

	@Bean
	public PostCodeQuery postcodeQuery() {
		return new PostCodeQuery(this.dataSource);
	}
}
