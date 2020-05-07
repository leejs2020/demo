package com.example.demo.mapper;

import java.util.List;

import com.example.demo.vo.BoardVo;

public interface BoardMapper {
	
	public List<BoardVo> selectBoardList();
}
