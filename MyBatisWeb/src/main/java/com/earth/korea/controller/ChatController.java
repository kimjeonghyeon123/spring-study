package com.earth.korea.controller;

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
	public String chat(String otherId, HttpSession session, Model m) {
		String loginId = (String) session.getAttribute("id");
		if(otherId != null) {
			try {
				Integer chatroomId = chattingService.getChattingRoomId(loginId, otherId);
				if(chatroomId == null) {
					throw new Exception();
				}
				m.addAttribute("chatroomId", chatroomId);
			} catch (Exception e) {
				e.printStackTrace();
				m.addAttribute("otherId", otherId);
			}
		}
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
	public ResponseEntity<Map<String, Object>> chatting(@RequestBody Map<String, Object> requestData) {
		Integer chatroomId = (Integer) requestData.get("chatroomId");
		String loginId = (String) requestData.get("loginId");
		
		List<ChattingDTO> list = null;
		
		try {
			list = chattingService.readChatting(chatroomId, loginId);
			int chatroomCnt = chattingService.getChatRoomCnt(chatroomId);
			
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
		Integer chatroomId = (Integer) requestData.get("chatroomId");
		String loginId = (String) requestData.get("loginId");
		String otherId = (String) requestData.get("otherId");
		String chat = (String) requestData.get("chat");
		
		if(chatroomId != null) {
			try {
				chattingService.sendChatting(chatroomId, loginId, chat);			
				return new ResponseEntity<Integer>(chatroomId, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			}
		}
		else {
			try {
				chatroomId = chattingService.makeChattingRoom(loginId, otherId, chat);
				return new ResponseEntity<Integer>(chatroomId, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@PostMapping("/deletechatting")
	@ResponseBody
	public ResponseEntity<String> deletechatting(@RequestBody Map<String, Object> requestData) {
		Integer chatroomId = (Integer) requestData.get("chatroomId");
		String loginId = (String) requestData.get("loginId");
		
		try {
			chattingService.deleteChattingRoom(chatroomId, loginId);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/chattingcnt")
	@ResponseBody
	public ResponseEntity<Integer> chattingcnt(@RequestBody Map<String, Object> requestData) {
		Integer chatroomId = (Integer) requestData.get("chatroomId");
		
		try {
			int cnt = chattingService.getChatRoomCnt(chatroomId);
			return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}
}
