package com.kkscom.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkscom.demo.dao.TestDao;

@Service
public class TestService {
	@Autowired
	private TestDao testDao;
	
	public String connTest() {
		return testDao.connTest();
	}

	public TestDao getTestDao() {
		return testDao;
	}

	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}
	
	
	
}
