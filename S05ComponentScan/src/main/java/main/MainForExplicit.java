package main;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import config.AppCtxWithExplicit;

public class MainForExplicit {

	private static AbstractApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(
				AppCtxWithExplicit.class);
		
		System.out.println("[컴포넌트 스캔]");
		System.out.println(" 중복 지정된 컴포넌트 스캔 제외 대상 지정");
		System.out.println(" - spring.dao.MemberDao");
		System.out.println(" - 수동으로 빈등록 메소드의 이름을 다르게 지정");
		System.out.println(" - @Bean public MemberDao memberDao2(){}");
		
		ctx.close();
	}

}