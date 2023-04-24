package typehier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx4Type {

	@Bean
	public ConsolePrinter printer() {
		return new ConsolePrinter();
	}
	
	/*
	 * 예제(MainForType4)에서 Printer.class이나 ConsolePrinter.class로
	 * 빈객체를 취득하려면 오류가 발생한다.
	 */
}
