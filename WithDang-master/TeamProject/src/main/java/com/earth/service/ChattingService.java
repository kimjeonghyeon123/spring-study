package com.earth.service;

import java.sql.Timestamp;
import java.util.List;

import com.earth.domain.ChatChatRoomDTO;
import com.earth.domain.ChatChattingDTO;
import com.earth.domain.ChatUserChatRoomDTO;

public interface ChattingService {
	
	//상대방이 채팅방 나갔는지 확인할 때 사용
	int getChatRoomCnt(Integer chatroom_id) throws Exception;
	
	//채팅방이 없으면 새 페이지로 감
	//채팅방이 있으면
	//안나갔으면 그대로 들어감
	//나갔으면 방 번호를 받아서 그 방에 보낼 수 있게 창이 뜸
	ChatUserChatRoomDTO getChattingRoom(String login_nickname, String other_nickname) throws Exception;
	
	//채팅방 만들기
	//채팅 새 페이지에서 채팅 보내기를 누르면 생성됨
	int makeChattingRoom(String login_nickname, String other_nickname, String message, Timestamp timestamp) throws Exception;
	
	//채팅방 목록 불러오기
	List<ChatChatRoomDTO> showChatRoomList(String login_nickname) throws Exception;
	
	//채팅 목록 불러오기
	List<ChatChattingDTO> readChatting(Integer chatroom_id, String login_nickname) throws Exception;
	
	//기존 채팅방에서 채팅 보내기
	void sendChatting(Integer chatroom_id, String login_nickname, String other_nickname, String message, Timestamp timestamp) throws Exception;
	
	//채팅방 나가기
	void deleteChattingRoom(Integer chatroom_id, String login_nickname) throws Exception;
	
}
