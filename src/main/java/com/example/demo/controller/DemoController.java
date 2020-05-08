package com.example.demo.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DemoService;
import com.example.demo.vo.DemoVo;
import com.google.gson.Gson;

@Named("demo")
@ViewScoped
@RestController
@RequestMapping("/board")
public class DemoController implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private DemoService service;

	public List<DemoVo> readList() {

		List<DemoVo> list = service.getList();

		Gson gson = new Gson();
		String s = gson.toJson(list);

		logger.info(s);
		return list;
	}

	public void writeBoard(DemoVo vo) {

		Gson gson = new Gson();
		String s = gson.toJson(vo);
		logger.info("===============================================");
		logger.debug(s);
		logger.info("===============================================");
		service.writeBoard(vo);
	}

	public void removeBoard() {
		String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("paramNo");
		
		Gson gson = new Gson();
		String s = gson.toJson(param);
		logger.info("===============================================");
		logger.debug(s);
		logger.info("===============================================");
		service.removeBoard(param);
	}
	
	@PostMapping("/removeApi")
	public void removeBoardRest(String no) {
		
		service.removeBoard(no);
	}
}
