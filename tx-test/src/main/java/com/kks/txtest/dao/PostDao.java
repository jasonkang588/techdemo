package com.kks.txtest.dao;

import java.sql.SQLException;
import java.util.List;

import com.kks.txtest.dto.Post;

public interface PostDao {
	public List<Post> findPostAll();
	public Post findPost(long no);
	public List<Post> findPost(Post dto);
	public void removePost(Post dto);
	public long registPost(Post dto);
	public void registPostBatch(List<Post> dtoList, int batchSize) throws SQLException;
}
