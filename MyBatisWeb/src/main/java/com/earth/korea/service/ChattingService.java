package com.earth.korea.service;

import java.util.List;

import com.earth.korea.domain.ChatRoomDTO;
import com.earth.korea.domain.ChattingDTO;

public interface ChattingService {

	//채팅방이 있으면 채팅방 번호을 주고 없으면 null을 넘김
	//채팅 페이지로 넘어감 or
	//채팅 새 페이지로 넘어감
	Integer getChattingRoomId(String login_id, String other_id) throws Exception;
	
	//채팅방 만들기
	//채팅 새 페이지에서 채팅 보내기를 누르면 생성됨
	int makeChattingRoom(String login_id, String other_id, String message) throws Exception;
	
	//채팅 목록 불러오기
	List<ChatRoomDTO> showChatRoomList(String login_id) throws Exception;
	
	//채팅 목록 불러오기
	List<ChattingDTO> readChatting(Integer chatroom_id, String login_id) throws Exception;
	
	//기존 채팅방에서 채팅 보내기
	void sendChatting(Integer chatrrom_id, String login_id, String message) throws Exception;
	
}
