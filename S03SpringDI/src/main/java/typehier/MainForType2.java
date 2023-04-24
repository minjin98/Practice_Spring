package typehier;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForType2 {

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx4Type.class);
		//인터페이스 Printer로 빈객체를 받겠다고 해 놓고
		// 구현 클래스인 ConsolePrinter로 받을 수 없다.
		//ConsolePrinter p1 = ctx.getBean("printer", Printer.class);
		
		Printer p1 = ctx.getBean("printer", Printer.class);
		ConsolePrinter p2 = ctx.getBean("printer", ConsolePrinter.class);
		System.out.println("p1 == p2 : " + (p1 == p2));
		ctx.close();
	}
}