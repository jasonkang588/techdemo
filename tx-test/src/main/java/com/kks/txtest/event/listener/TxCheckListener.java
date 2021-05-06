package com.kks.txtest.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.kks.txtest.event.TxCheckEvent;

@Service
public class TxCheckListener implements ApplicationListener<TxCheckEvent> {

//	@Async
//	public void onApplicationEvent(TxCheckEvent event) {
//		String tname = Thread.currentThread().getName();
//		long tid = Thread.currentThread().getId();
//		System.out.println("listener thread info = { name :" + tname + ", id : " + tid);
//		
//		System.out.println("txcheckevent recieved!!");
//		System.out.println("event will fire action (name=" + event.getAction() + ")");
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("Thread sleeped 3sec.");
//	}
	
	public void onApplicationEvent(TxCheckEvent event) {
		this.executeAsync(event);
	}
	
	public void executeAsync(final TxCheckEvent event) {
		Thread t = new Thread(new Runnable() {
			
			public void run() {
				String tname = Thread.currentThread().getName();
				long tid = Thread.currentThread().getId();
				System.out.println("listener thread info = { name :" + tname + ", id : " + tid);
				
				System.out.println("txcheckevent recieved!!");
				System.out.println("event will fire action (name=" + event.getAction() + ")");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Thread sleeped 3sec.");
			}
		});
		
		t.start();
	}
	
	
	
}
