package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.DemoMapper;
import com.example.demo.vo.DemoVo;

@Service
public class DemoService {

	@Autowired
	private DemoMapper mapper;

	public List<DemoVo> getList() {

		return mapper.selectBoardList();
	}

	public void writeBoard(DemoVo vo) {
		
		mapper.insertBoard(vo);
	}
	
	public void removeBoard(String no) {
		mapper.deleteBoard(no);
	}
}
