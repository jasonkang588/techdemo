package com.kks.txtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kks.txtest.context.AppContextHolder;
import com.kks.txtest.dao.BoardDao;
import com.kks.txtest.dto.Post;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	public List<Post> getPostList() {
		return boardDao.findPostAll();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void removePost() {
		Post dto = new Post();
		dto.setNo(271);
		boardDao.removePost(dto);
		System.out.println("removed....?");
	}
}
