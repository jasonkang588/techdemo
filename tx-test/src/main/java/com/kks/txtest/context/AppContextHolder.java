package com.kks.txtest.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AppContextHolder {
	private static ApplicationContext ctx;
	
	public static ApplicationContext getApplicationContext() {
		if(ctx == null) {
			ctx = new ClassPathXmlApplicationContext("classpath:config/spring/root-context.xml");	
		}
		return ctx;
	}
}
