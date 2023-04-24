package singleton;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(new AppContext());
		Greeter g1 = (Greeter)ctx.getBean("greeter");
		Greeter g2 = (Greeter)ctx.getBean("greeter");
		System.out.println("(g1 == g2) = " + (g1 == g2)); // 싱글톤 객체이므로 true
		ctx.close();
	}
}
