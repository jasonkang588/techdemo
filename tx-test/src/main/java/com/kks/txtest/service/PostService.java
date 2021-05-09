package com.kks.txtest.service;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kks.txtest.dao.PostDao;
import com.kks.txtest.dto.Post;
import com.kks.txtest.event.TxCheckEvent;

@Service
public class PostService {
	@Autowired
	@Qualifier("postDaoImpl")
	private PostDao postDao;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Transactional(readOnly=true)
	public List<Post> getPostList() {
		try {
			return postDao.findPostAll();	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void removePost(long no) {
		Post dto = new Post();
		dto.setNo(no);
		postDao.removePost(dto);
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
	
	@Transactional(propagation=Propagation.REQUIRED)
	public long post(String title, String content, String creater) {
		Post post = new Post();
		post.setTitle(title);
		post.setContent(content);
		post.setCreateBy(creater);
		post.setUpdateBy(creater);
		return postDao.registPost(post);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public long post(Post dto) {
		return postDao.registPost(dto);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void post(List<Post> dtoList) {
		int idx=0;
		for(Post dto : dtoList) {
			this.post(dto);
			idx++;
			if(idx % 1000 == 0) {
				System.out.println("insert 1000 completed.");
			}
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void postBatch(List<Post> dtoList, int batchSize) throws SQLException {
		postDao.registPostBatch(dtoList, batchSize);
		//throw new SQLException("test");
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void postHybrid(List<Post> dtoList) throws Exception {
		int pivot = dtoList.size()/3;
		for(int i=0; i<pivot; i++) {
			this.post(dtoList.get(i));	
		}
		
		this.postBatch(dtoList.subList(pivot, dtoList.size()), 5000);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void postThread(List<Post> list) {
//		ExecutorService es = Executors.newFixedThreadPool(10);
//		for(final Post post : list) {
//			es.execute(new Runnable() {
//				public void run() {
//					System.out.println("no" + post(post) + " inserted.");
//				}
//			});	
//		}
//		
//		es.shutdown();
//		
//		while(es.isTerminated()) {
//			
//		}
		
		{
			for(Post dto : list) {
				this.post(dto);
			}
		}
		
		
		System.out.println("finished.");
	}

	/**
	 * thread 별로 커넥션이 새로 생성되기 때문에 트랜잭션 처리가 되지 않는다. 각 thread별로 새로운 트랜잭션이 되어버림.
	 * @param list
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void postThread2(List<Post> list) {
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(final Post post : list) {
			es.execute(new Runnable() {
				public void run() {
					postDao.registPost(post);
				}
			});	
		}
		
		es.shutdown();
		
		while(es.isTerminated()) {
			
		}
		
		
		
		System.out.println("finished.");
	}
}
