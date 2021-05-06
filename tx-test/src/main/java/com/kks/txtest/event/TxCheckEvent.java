package com.kks.txtest.event;

import org.springframework.context.ApplicationEvent;

public class TxCheckEvent extends ApplicationEvent {
	String action;
	
	public TxCheckEvent(String action) {
		super(action);
		this.action = action;
	}
	
	public String getAction() {
		return action;
	}
}
