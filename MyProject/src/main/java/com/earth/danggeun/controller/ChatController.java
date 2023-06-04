package com.earth.danggeun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.earth.danggeun.domain.ChatRoomDTO;
import com.earth.danggeun.domain.ChattingDTO;
import com.earth.danggeun.service.ChattingService;

@Controller
public class ChatController {

	private ChattingService chattingService;

	public ChatController(ChattingService chattingService) {
		this.chattingService = chattingService;
	}
	
	@GetMapping("/chat")
	public String chat(String other_email, HttpSession session, Model m) {
		Integer chatroom_id = null;
		String login_email = (String) session.getAttribute("email");
		if(other_email != null) {
			try {
				chatroom_id = chattingService.getChattingRoomId(login_email, other_email);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(chatroom_id != null) {
				m.addAttribute("msg", "CHAT_EXIST");
			}
			else {
				m.addAttribute("msg", "CHAT_NOTEXIST");
				m.addAttribute("other_email", other_email);
			}
		}
		return "chat";
	}

	@PostMapping("/chats")
	@ResponseBody
	public ResponseEntity<List<ChatRoomDTO>> chat(@RequestBody Map<String, String> requestData) {
		String login_email = requestData.get("login_email");
		List<ChatRoomDTO> list = null;
		try {
			list = chattingService.showChatRoomList(login_email);
			return new ResponseEntity<List<ChatRoomDTO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ChatRoomDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/chattings")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> chatting(@RequestBody Map<String, Object> requestData) {
		Integer chatroom_id = (Integer) requestData.get("chatroom_id");
		String login_email = (String) requestData.get("login_email");
		
		List<ChattingDTO> list = null;
		
		try {
			list = chattingService.readChatting(chatroom_id, login_email);
			int chatroomCnt = chattingService.getChatRoomCnt(chatroom_id);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("chatroomCnt", chatroomCnt);
			
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/sendchatting")
	@ResponseBody
	public ResponseEntity<Integer> sendchatting(@RequestBody Map<String, Object> requestData) {
		Integer chatroom_id = (Integer) requestData.get("chatroom_id");
		String login_email = (String) requestData.get("login_email");
		String other_email = (String) requestData.get("other_email");
		String chat = (String) requestData.get("chat");
		
		if(chatroom_id != null) {
			try {
				chattingService.sendChatting(chatroom_id, login_email, chat);			
				return new ResponseEntity<Integer>(chatroom_id, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			}
		}
		else {
			try {
				chatroom_id = chattingService.makeChattingRoom(login_email, other_email, chat);
				return new ResponseEntity<Integer>(chatroom_id, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@PostMapping("/deletechatting")
	@ResponseBody
	public ResponseEntity<String> deletechatting(@RequestBody Map<String, Object> requestData) {
		Integer chatroom_id = (Integer) requestData.get("chatroom_id");
		String login_email = (String) requestData.get("login_email");
		
		try {
			chattingService.deleteChattingRoom(chatroom_id, login_email);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/chattingcnt")
	@ResponseBody
	public ResponseEntity<Integer> chattingcnt(@RequestBody Map<String, Object> requestData) {
		Integer chatroom_id = (Integer) requestData.get("chatroom_id");
		
		try {
			int cnt = chattingService.getChatRoomCnt(chatroom_id);
			return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}
}
