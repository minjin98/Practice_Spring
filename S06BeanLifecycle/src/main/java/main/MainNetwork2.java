package main;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import config.AppCtx;
import network.NetworkBean;
import network.NetworkBean2;
import spring.Client;

public class MainNetwork2 {

	public static void main(String[] args) throws IOException {
		System.out.println("[생명주기관리 테스트]");
		
		System.out.println("1. 빈객체 등록");
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		System.out.println("2. 빈객체 가져오기");
		NetworkBean2 net = ctx.getBean(NetworkBean2.class);
		
		System.out.println("3. 빈객체 실행");
		net.send("접속을 환영합니다.");
		
		//------------------------------------------------------
		
		System.out.println("4. 빈객체 사용 종료");
		ctx.close();
	}

}