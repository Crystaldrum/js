package com.smee.e.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO {
	
	@Autowired
	private SqlSession sqlsession;
	
	public ArrayList<String> getDoroCD(String testGu) {
		ArrayList<String> result = new ArrayList<>();
		System.out.println("TestDAO : " + testGu);
		
		TestMapper mapper = sqlsession.getMapper(TestMapper.class);
		result = mapper.getDoroCD(testGu);
		System.out.println(result);
		
		return result;
	}
}
