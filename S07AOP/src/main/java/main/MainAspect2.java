package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap07.Calculator;
import chap07.ImpeCalculator;
import config.AppCtx2;

public class MainAspect2 {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx2.class);

		Calculator cal = ctx.getBean("calculator", Calculator.class);
		long fiveFact = cal.factorial(5);
		System.out.println("cal.factorial(5) = " + fiveFact);
		System.out.println(cal.getClass().getName());
		System.out.println("-----------------------------------------------------------");
	
		// Aspect는 인터페이스 타입으로 받아야 한다.
		// 클래스 타입으로 받으려면 @EnableAspectJAutoProxy(proxyTargetClass = true)
		Calculator cal2 = ctx.getBean("impeCalculator", Calculator.class);
		long fiveFact2 = cal2.factorial(5);
		System.out.println("cal2.factorial(5) = " + fiveFact2);
		System.out.println(cal2.getClass().getName());
		
		ctx.close();
	}
	public static void beanList() {
		
		
	}

}
