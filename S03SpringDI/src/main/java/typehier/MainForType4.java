package typehier;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForType4 {

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx4Type.class);
		
		/*
		 * 빈객체 ㅣ름을 지정하지 않고 클래스.class로 빈객체를 취득하면
		 * 동일한 타입으로 리턴하는 빈객체가 유일해야 한다.  
		 */
		Printer p1 = ctx.getBean("printer", Printer.class);
		Printer p2 = ctx.getBean("printer", ConsolePrinter.class);
		System.out.println("p1 == p2 : " + (p1 == p2));
		
		p1.print("p1");
		p2.print("p2");
		ctx.close();
	}
}