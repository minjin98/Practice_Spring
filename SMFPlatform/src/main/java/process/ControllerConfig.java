package process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import process.ProcessController;
import process.ProcessDao;


@Configuration
public class ControllerConfig {
	
	@Autowired
	private ProcessDao processDao;
		
	@Bean
	public ProcessController processController() {
		return new ProcessController(processDao);
	}
	
}
