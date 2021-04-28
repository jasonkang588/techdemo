package com.kks.txtest.dao;

import java.util.List;

import com.kks.txtest.dto.Post;

public interface BoardDao {
	public List<Post> findPostAll();
	public Post findPost(long no);
	public List<Post> findPost(Post dto);
	public void removePost(Post dto);
}
