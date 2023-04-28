package main;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;

public class Main {

	private static AnnotationConfigApplicationContext ctx = null;

	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		ctx.close();
	}

}