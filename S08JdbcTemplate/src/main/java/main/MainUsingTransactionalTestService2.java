package main;

import java.time.LocalDateTime;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import dbquery.DbQueryTR;
import spring.MemberInfoPrinter;
import spring.dao.Member;
import spring.services.TransactionalTestService;

public class MainUsingTransactionalTestService2 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		TransactionalTestService tts1 = ctx.getBean(TransactionalTestService.class);
		tts1.tranactionOne(new Member("tts1@tts.co.kr", "1234", "TTS", LocalDateTime.now()));
		System.out.println("----------------------------------------------------------------------------------------------------");
		
		TransactionalTestService tts2 = ctx.getBean(TransactionalTestService.class);
		tts2.tranactionTwo(new Member("tts2@tts.co.kr", "1234", "TTS", LocalDateTime.now()));
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println("tts1 == tts2 ? " + (tts1 == tts2));
		System.out.println("----------------------------------------------------------------------------------------------------");
		MemberInfoPrinter info = ctx.getBean(MemberInfoPrinter.class);
		info.printMemberInfo("tts2@tts.co.kr");
		ctx.close();
	}
}
