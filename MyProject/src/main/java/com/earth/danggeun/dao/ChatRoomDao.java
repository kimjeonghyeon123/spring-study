package com.earth.danggeun.dao;

import java.util.List;

import com.earth.danggeun.domain.ChatRoomDTO;

public interface ChatRoomDao {

	//모든 채팅방 목록 가져오기
	List<ChatRoomDTO> selectAll(String login_email) throws Exception;
	
	//채팅방 만들기
	int insert(ChatRoomDTO chatRoomDTO) throws Exception;
	
	//채팅방 최신메시지 업데이트하기
	int updateChatRoom(ChatRoomDTO chatRoomDTO) throws Exception;
	
	//채팅방 읽은 상태로 만들기
	int updateUnreadCntToZero(Integer id, String login_email) throws Exception;
	
	//채팅방 삭제할 때 사용
	int delete(Integer id) throws Exception;
	
}
