package com.earth.korea.dao;

import java.util.List;

import com.earth.korea.domain.ChatRoomDTO;

public interface ChatRoomDao {

	//모든 채팅방 목록 가져오기
	List<ChatRoomDTO> selectAll(String login_id) throws Exception;
	
	//채팅방 만들기
	int insert(ChatRoomDTO chatRoomDTO) throws Exception;
	
	//채팅방 최신메시지 업데이트하기
	int updateChatRoom(ChatRoomDTO chatRoomDTO) throws Exception;
	
	//채팅방 읽은 상태로 만들기
	int updateUnreadCntToZero(Integer id, String login_id) throws Exception;
	
}
