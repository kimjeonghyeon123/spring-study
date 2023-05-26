package com.earth.korea.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.earth.korea.domain.ChatRoomDTO;
import com.earth.korea.domain.ChattingDTO;
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
	
	@PostMapping("/chats")
	@ResponseBody
	public ResponseEntity<List<ChatRoomDTO>> chat(@RequestBody Map<String, String> requestData) {
		String login_id = requestData.get("loginId");
		List<ChatRoomDTO> list = null;
		
		try {
			list = chattingService.showChatRoomList(login_id);
			return new ResponseEntity<List<ChatRoomDTO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ChatRoomDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/chattings")
	@ResponseBody
	public ResponseEntity<List<ChattingDTO>> chatting(@RequestBody Map<String, Object> requestData) {
		Integer chatroomId = (Integer) requestData.get("chatroomId");
		String loginId = (String) requestData.get("loginId");
		
		List<ChattingDTO> list = null;
		
		try {
			list = chattingService.readChatting(chatroomId, loginId);
			return new ResponseEntity<List<ChattingDTO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ChattingDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/sendchatting")
	@ResponseBody
	public ResponseEntity<String> sendchatting(@RequestBody Map<String, Object> requestData) {
		Integer chatroomId = (Integer) requestData.get("chatroomId");
		String loginId = (String) requestData.get("loginId");
		String chat = (String) requestData.get("chat");
		
		try {
			chattingService.sendChatting(chatroomId, loginId, chat);			
			return new ResponseEntity<String>("SEND_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("SEND_ERR", HttpStatus.BAD_REQUEST);
		}
	}
}
