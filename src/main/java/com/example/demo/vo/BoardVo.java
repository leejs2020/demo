package com.example.demo.vo;

import lombok.Data;

@Data
public class BoardVo {

	public String no;
	public String userId;
	public String subject;
	public String createDate;
	public String editDate;
	public String deleteYn;
}
