package com.earth.korea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.earth.korea.domain.ChatDTO;
import com.earth.korea.service.ChatService;

@Controller
public class ChatController {

	@Autowired
	ChatService service;
	
	@GetMapping("/chatroom")
	public String chatroom() {
		return "chat";
	}
	
	@PostMapping("/chat")
	public ResponseEntity<String> write(@RequestBody ChatDTO chatDTO) {
		try {
			if(service.insert(chatDTO) != 1)
				throw new Exception("write Failed");

			return new ResponseEntity<String>("WRT_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/chat")
	@ResponseBody
	ResponseEntity<List<ChatDTO>> list() {
		List<ChatDTO> list = null;
		
		try {
			list = service.loadChatting();
			return new ResponseEntity<List<ChatDTO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ChatDTO>>(list, HttpStatus.BAD_REQUEST);
		}
	}
	
}
