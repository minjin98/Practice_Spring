package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main3 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		Greeter g1 = ctx.getBean("greeter", Greeter.class);
		String msg = g1.greet("[프레임워크] 스프링");
		System.out.println(msg);
		
		Greeter g2 = createGreeter();
		String msg2 = g2.greet("[직접생성객체] Spring");
		System.out.println(msg2);
		
		System.out.println("(g1 == g2) = " + (g1 == g2)); // true : 스프링과 객체 공유
		ctx.close();
	}
	
	static Greeter createGreeter() {
		Greeter g = Greeter.getInstance();
		g.setFormat("%s, 환영합니다.");
		return g;
	}
}
