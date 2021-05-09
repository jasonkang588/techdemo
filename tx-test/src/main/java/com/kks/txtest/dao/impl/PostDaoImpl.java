package com.kks.txtest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.kks.txtest.dao.PostDao;
import com.kks.txtest.dto.Post;

@Repository
public class PostDaoImpl extends SqlMapClientDaoSupport implements PostDao{
	
	@Autowired
	@Qualifier("defaultSqlMapClient")
	public void setSqlMapClientSuper(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	public List<Post> findPostAll() {
		return this.getSqlMapClientTemplate().queryForList("test.selectPost", null);
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

	public long registPost(Post dto) {
		return (Long) this.getSqlMapClientTemplate().insert("test.insertPost", dto);
	}

	public void registPostBatch(List<Post> dtoList, int batchSize) throws SQLException {
		SqlMapClient c = this.getSqlMapClientTemplate().getSqlMapClient();
		try {
			c.startTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			c.getCurrentConnection().setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			c.startBatch();
			int idx = 0;
			for(Post post : dtoList) {
				c.insert("test.insertPost", post);
				idx++;
				if(idx % batchSize == 0) {
					int affect = c.executeBatch();
					System.out.println("executeBatch completed. affected : " + affect);
					c.startBatch();
				}
			}
			int affect = c.executeBatch();
			System.out.println("executeBatch completed. affected : " + affect);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
	}

}
