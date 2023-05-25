package com.earth.korea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earth.korea.dao.ChatDao;
import com.earth.korea.domain.ChatDTO;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	ChatDao chatDao;
	
	@Override
	public List<ChatDTO> loadChatting() throws Exception {
		return chatDao.selectAll();
	}

	@Override
	public int insert(ChatDTO chatDTO) throws Exception {
		return chatDao.insert(chatDTO);
	}

	
	
}
