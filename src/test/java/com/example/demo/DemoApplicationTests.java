package com.example.demo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private SqlSessionFactory sqlFactory;

	@Test
	void contextLoads() {
	}

	@Test
	void connTest() {
		try (SqlSession session = sqlFactory.openSession()) {

			System.out.println(session);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
