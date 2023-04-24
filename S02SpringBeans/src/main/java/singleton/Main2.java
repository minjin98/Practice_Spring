package singleton;

public class Main2 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(new AppContext());
		Greeter g = (Greeter)ctx.getBean("greeter");
		String msg = g.greet("스프링");
		System.out.println(msg);
		
		Greeter g2 = (Greeter)ctx.getBean("greeter");
		String msg2 = g2.greet("JSP");
		System.out.println(msg2);
		ctx.close();
	}
}
