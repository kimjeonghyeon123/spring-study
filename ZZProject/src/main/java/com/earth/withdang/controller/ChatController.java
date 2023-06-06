package com.earth.withdang.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.earth.withdang.domain.ChatChatRoomDTO;
import com.earth.withdang.domain.ChatChattingDTO;
import com.earth.withdang.service.ChattingService;

@Controller
@RequestMapping("/chat")
public class ChatController {

	private ChattingService chattingService;
	
	public ChatController(ChattingService chattingService) {
		this.chattingService = chattingService;
	}
	
	@GetMapping("/chatroom")
	public String chat(String other_nickname, HttpSession session, Model m) {
		Integer chatroom_id = null;
		String login_nickname = (String) session.getAttribute("nickname");
		if(other_nickname != null) {
			try {
				chatroom_id = chattingService.getChattingRoomId(login_nickname, other_nickname);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(chatroom_id != null) {
				m.addAttribute("msg", "CHAT_EXIST");
			}
			else {
				m.addAttribute("msg", "CHAT_NOTEXIST");
				m.addAttribute("other_nickname", other_nickname);
			}
		}
		return "chat";
	}
	
	@PostMapping("/showchatrooms")
	@ResponseBody
	public ResponseEntity<List<ChatChatRoomDTO>> chat(@RequestBody Map<String, String> requestData) {
		String login_nickname = requestData.get("login_nickname");
		List<ChatChatRoomDTO> list = null;
		try {
			list = chattingService.showChatRoomList(login_nickname);
			return new ResponseEntity<List<ChatChatRoomDTO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ChatChatRoomDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/showchattings")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> chatting(@RequestBody Map<String, Object> requestData) {
		Integer chatroom_id = (Integer) requestData.get("chatroom_id");
		String login_nickname = (String) requestData.get("login_nickname");
		
		List<ChatChattingDTO> list = null;
		
		try {
			list = chattingService.readChatting(chatroom_id, login_nickname);
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
		String login_nickname = (String) requestData.get("login_nickname");
		String other_nickname = (String) requestData.get("other_nickname");
		String chat = (String) requestData.get("chat");
		
		if(chatroom_id != null) {
			try {
				chattingService.sendChatting(chatroom_id, login_nickname, chat);			
				return new ResponseEntity<Integer>(chatroom_id, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			}
		}
		else {
			try {
				chatroom_id = chattingService.makeChattingRoom(login_nickname, other_nickname, chat);
				return new ResponseEntity<Integer>(chatroom_id, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@PostMapping("/deletechatroom")
	@ResponseBody
	public ResponseEntity<String> deletechatting(@RequestBody Map<String, Object> requestData) {
		Integer chatroom_id = (Integer) requestData.get("chatroom_id");
		String login_nickname = (String) requestData.get("login_nickname");
		
		try {
			chattingService.deleteChattingRoom(chatroom_id, login_nickname);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/getchattingcnt")
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










