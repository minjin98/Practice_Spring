package main;

import java.time.LocalDateTime;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import exercise.dao.Member;
import exercise.services.TransactionalTestService;

public class MainUsingTransactionalTestService {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		TransactionalTestService tts = ctx.getBean(TransactionalTestService.class);
		tts.tranactionOne(new Member("tts@tts.co.kr", "1234", "TTS", LocalDateTime.now()));
		tts.tranactionTwo(new Member("tts@tts.co.kr", "1234", "TTS", LocalDateTime.now()));
		
		ctx.close();
	}
}
