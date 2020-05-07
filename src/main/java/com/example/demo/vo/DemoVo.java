package com.example.demo.vo;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import lombok.Data;

@Data
@Named("demoVo")
@RequestScoped
public class DemoVo {

	public String no;
	public String userId;
	public String subject;
	public String createDate;
	public String editDate;
	public String deleteYn;
}
