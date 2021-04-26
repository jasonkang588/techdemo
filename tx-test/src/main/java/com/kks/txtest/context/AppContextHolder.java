package com.kks.txtest.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContextHolder {
	public ApplicationContext getApplicationContext() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:config/spring/root-context.xml");
		
		return ctx;
	}
	
	public static void main(String[] args) {
		AppContextHolder h = new AppContextHolder();
		h.getApplicationContext();
	}
}
