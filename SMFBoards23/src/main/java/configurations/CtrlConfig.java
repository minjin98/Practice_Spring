package configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import boards.controllers.BoardController;
import home.HelloController;


@Configuration
public class CtrlConfig {

	@Bean
	public HelloController helloController() {
		return new HelloController();
	}
	
	@Bean
	public BoardController boardController() {
		return new BoardController();
	}

}
