package com.kks.txtest.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AppContextHolder {

	private static class LazyLoader {
		private static ApplicationContext ctx;
		static {
			ctx = new ClassPathXmlApplicationContext("classpath:config/spring/root-context.xml");
		}
	}
	
	public static ApplicationContext getApplicationContext() {
		return LazyLoader.ctx;
	}
	
}
