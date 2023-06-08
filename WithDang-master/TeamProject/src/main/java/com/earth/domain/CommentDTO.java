package com.earth.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CommentDTO {
	
	private int cmt_id;
	private String cmt_content;
	private Date cmt_created_time;
	private int post_id;
	private String user_email;
	private String user_nickname;	//유저 닉네임 추가 
	
}
	