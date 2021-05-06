package com.kks.txtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kks.txtest.context.AppContextHolder;
import com.kks.txtest.dao.BoardDao;
import com.kks.txtest.dto.Post;
import com.kks.txtest.event.TxCheckEvent;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	public List<Post> getPostList() {
		return boardDao.findPostAll();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void removePost(long no) {
		Post dto = new Post();
		dto.setNo(no);
		boardDao.removePost(dto);
		System.out.println("removed....?");
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void removePost(long[] nos) {
		for(long no : nos) {
			removePost(no);
		}
		//throw new RuntimeException();
	}
	
	public void pubEventTest() {
		String tname = Thread.currentThread().getName();
		long tid = Thread.currentThread().getId();
		System.out.println("publisher thread info = { name :" + tname + ", id : " + tid);
		String action = "checkTx";
		System.out.println("action name:" + action + " will be pub");
		this.applicationEventPublisher.publishEvent(new TxCheckEvent(action));
		System.out.println("action name:" + action + " has been published.");
	}

}
