package com.kks.txtest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.kks.txtest.dao.BoardDao;
import com.kks.txtest.dto.Post;

public class BoardDaoImpl extends SqlMapClientDaoSupport implements BoardDao{

	public List<Post> findPostAll() {
		List<Post> result = null;
		result = this.getSqlMapClientTemplate().queryForList("test.selectBoard", null);
		try {
			result = this.getSqlMapClient().queryForList("test.selectBoard", null);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println("done....");
		return result;
	}

	public Post findPost(long no) {
		// TODO Auto-generated method stub
	
		return null;
	}

	public List<Post> findPost(Post dto) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void removePost(Post dto) {
		this.getSqlMapClientTemplate().delete("test.deletePost", dto);
//		try {
//			this.getSqlMapClientTemplate().getSqlMapClient().delete("test.deletePost", dto);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
