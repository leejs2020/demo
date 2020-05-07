package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.DemoVo;

@Repository
public class DemoMapper {

	@Autowired
	private SqlSession sqlSession;

	public List<DemoVo> selectBoardList() {

		return sqlSession.selectList("selectBoardList2");

	}
	
	public void insertBoard(DemoVo vo){
		sqlSession.insert("insertBoard", vo);
	}
	
	public void deleteBoard(String no) {
		sqlSession.update("deleteBoard", no);
	}
}
