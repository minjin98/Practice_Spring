package main;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import config.AppCtxManual1;
import config.AppCtxManual2;

public class MainForManual {

	private static AbstractApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtxManual1.class, AppCtxManual2.class);
	
		System.out.println("[빈객체 목록]");
		String[] names = ctx.getBeanDefinitionNames();
		Arrays.stream(names).forEach(name -> System.out.println(name));
		System.out.println("----------------------------------------");
		System.out.println("(memberRegisterService 중복 등록)");
		System.out.println(" - 매뉴얼로 빈객체 등록하면 Overriding 된다.");
		System.out.println(" - 최종적으로 하나만 등록된다.");
		ctx.close();
	}

}