package com.kks.txtest.service;

import java.util.List;

import com.kks.txtest.context.AppContextHolder;
import com.kks.txtest.dao.BoardDao;
import com.kks.txtest.dto.Post;

public class BoardService {
	BoardDao dao = (BoardDao) AppContextHolder.getApplicationContext().getBean("boardDao");
	
	public static BoardService getInstance() {
		return LazyLoader.INSTANCE;
	}
	
	private static class LazyLoader {
		public static final BoardService INSTANCE = new BoardService();
	}
	
	public List<Post> getPostList() {
		return dao.findPostAll();
	}
}
