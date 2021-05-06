package com.kkscom.demo.dao;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {
	
	@Autowired
	@Qualifier(value="sampleSqlSession")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
	  this.sqlSession = sqlSession;
	}
	
	@PostConstruct
	public void test() {
		System.out.println("dao test constructed!!");
	}
	
	public String connTest() {
		return this.sqlSession.selectOne("daoTest.selectConnSuccessMsg");
	}
	
}
