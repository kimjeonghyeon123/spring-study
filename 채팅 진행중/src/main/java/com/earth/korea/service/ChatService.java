package com.earth.korea.service;

import java.util.List;

import com.earth.korea.domain.ChatDTO;

public interface ChatService {
	
	List<ChatDTO> loadChatting() throws Exception;
	
	int insert(ChatDTO chatDTO) throws Exception;
	
}
