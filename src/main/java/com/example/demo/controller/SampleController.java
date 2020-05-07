package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BoardService;
import com.example.demo.vo.BoardVo;

@RestController
@RequestMapping("/")
public class SampleController {

	@Autowired
	private BoardService service;

	@GetMapping("/readList")
	public List<BoardVo> getBoardList() {

		return service.selectBoardList();
	}
	
}
