package main;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import config.AppCtxWithPrototype;
import spring.Client;

public class MainWithPrototype {

	public static void main(String[] args) throws IOException {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtxWithPrototype.class);

		Client client1 = ctx.getBean(Client.class);
		Client client2 = ctx.getBean(Client.class);
		
		//Client의 Scope("prototype")으로 되어 있기 때문에
		//client1과 client는 서로 다른 객체이다.
		System.out.println("client1 == client2 : " + (client1 == client2)); // false

		ctx.close();
		
		// Client의 Scope("prototype")으로 되어 있기 때문에 Client.destroy()가 호출되지 않는다.
	}

}