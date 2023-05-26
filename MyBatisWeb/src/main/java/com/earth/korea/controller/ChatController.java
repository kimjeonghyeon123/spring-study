package com.earth.korea.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.earth.korea.domain.ChatRoomDTO;
import com.earth.korea.service.ChattingService;

@Controller
public class ChatController {

	private ChattingService chattingService;

	public ChatController(ChattingService chattingService) {
		this.chattingService = chattingService;
	}
	
	@GetMapping("/chat")
	public String chat() {
		return "chat";
	}
	
	@GetMapping("/chats")
	@ResponseBody
	public ResponseEntity<List<ChatRoomDTO>> chat(String login_id) {
		List<ChatRoomDTO> list = null;
		
		try {
			list = chattingService.showChatRoomList(login_id);
			return new ResponseEntity<List<ChatRoomDTO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ChatRoomDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
