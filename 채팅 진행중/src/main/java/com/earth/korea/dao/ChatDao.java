package com.earth.korea.dao;

import java.util.List;

import com.earth.korea.domain.ChatDTO;

public interface ChatDao {

	List<ChatDTO> selectAll() throws Exception;
	
	int insert(ChatDTO chatDTO) throws Exception;
	
}
