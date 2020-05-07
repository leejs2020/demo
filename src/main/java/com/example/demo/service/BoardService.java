package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardMapper mapper;
	
	public List<BoardVo> selectBoardList(){

		return mapper.selectBoardList();
	}
}
