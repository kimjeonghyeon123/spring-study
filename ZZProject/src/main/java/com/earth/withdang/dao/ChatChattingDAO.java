package com.earth.withdang.dao;

import java.util.List;

import com.earth.withdang.domain.ChatChattingDTO;

public interface ChatChattingDAO {
	
	//채팅방 가져오기
	List<ChatChattingDTO> selectAll(Integer chatroom_id) throws Exception;
	
	//채팅 보내기
	int insert(ChatChattingDTO chatChattingDTO) throws Exception;
	
	//채팅 읽음 상태로 변경하기
	int updateCheckReadToTrue(Integer chatroom_id, String login_nickname) throws Exception;
	
}
