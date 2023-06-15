package com.earth.withdang;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.earth.domain.ChatChatRoomDTO;
import com.earth.domain.ChatChattingDTO;
import com.earth.domain.ChatUserChatRoomDTO;
import com.earth.domain.MemberDto;
import com.earth.service.ChattingService;
import com.earth.service.MemberService;

@Controller
@RequestMapping("/chat")
public class ChatController {

	private ChattingService chattingService;
	private MemberService memberService;
	
	public ChatController(ChattingService chattingService, MemberService memberService) {
		this.chattingService = chattingService;
		this.memberService = memberService;
	}

	@GetMapping("/chatroom")
	public String chat(String other_nickname, HttpSession session, Model m, HttpServletRequest request) {
		if(!loginCheck(request)) {
			return "redirect:/login";
		}
		
		String login_nickname = (String) session.getAttribute("nickname");
		if(other_nickname != null) {
			try {
				ChatUserChatRoomDTO chatUserChatRoomDTO = chattingService.getChattingRoom(login_nickname, other_nickname);
				if(chatUserChatRoomDTO != null) {
					if(chatUserChatRoomDTO.getParticipation()) {
						m.addAttribute("msg", "CHAT_EXIST");
					}
					else {
						m.addAttribute("chatroom_id", chatUserChatRoomDTO.getChatroom_id());
					}
				}
				
				m.addAttribute("other_nickname", other_nickname);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return "chat";
	}
	
	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("nickname") != null && session.getAttribute("nickname") != "";
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
	public ResponseEntity<List<ChatChattingDTO>> chatting(@RequestBody Map<String, Object> requestData) {
		Integer chatroom_id = (Integer) requestData.get("chatroom_id");
		String login_nickname = (String) requestData.get("login_nickname");
		String other_nickname = (String) requestData.get("other_nickname");
		List<ChatChattingDTO> list = null;
		
		try {
			list = chattingService.readChatting(chatroom_id, login_nickname, other_nickname);
			
			return new ResponseEntity<List<ChatChattingDTO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ChatChattingDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/sendchatting")
	@ResponseBody
	public ResponseEntity<Integer> sendchatting(@RequestBody Map<String, Object> requestData) {
		Integer chatroom_id = (Integer) requestData.get("chatroom_id");
		String login_nickname = (String) requestData.get("login_nickname");
		String other_nickname = (String) requestData.get("other_nickname");
		String chat = (String) requestData.get("chat");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		if(chatroom_id != null) {
			try {
				chattingService.sendChatting(chatroom_id, login_nickname, other_nickname, chat, timestamp);			
				return new ResponseEntity<Integer>(chatroom_id, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			}
		}
		else {
			try {
				chatroom_id = chattingService.makeChattingRoom(login_nickname, other_nickname, chat, timestamp);
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
	
}










