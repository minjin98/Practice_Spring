package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import network.NetworkBean;
import network.NetworkBean2;
import spring.Client;
import spring.Client2;

@Configuration
public class AppCtx {

	@Bean
	public Client client() {
		Client client = new Client();
		client.setHost("host");
		return client;
	}
	
	@Bean(initMethod = "connect", destroyMethod = "close")
	public Client2 client2() {
		Client2 client = new Client2();
		client.setHost("host");
		return client;
	}
	
	@Bean(initMethod = "open", destroyMethod = "close")
	public NetworkBean network() {
		return new NetworkBean();
	}
	
	@Bean 
	public NetworkBean2 network2() {
		return new NetworkBean2();
	}
	
	
}
