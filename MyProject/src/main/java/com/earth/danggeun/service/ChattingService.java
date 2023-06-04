package com.earth.danggeun.service;

import java.util.List;

import com.earth.danggeun.domain.ChatRoomDTO;
import com.earth.danggeun.domain.ChattingDTO;

public interface ChattingService {
	
	//상대방이 채팅방 나갔는지 확인할 때 사용
	int getChatRoomCnt(Integer chatroom_id) throws Exception;
	
	//채팅방이 있으면 채팅방 번호을 주고 없으면 null을 넘김
	//채팅 페이지로 넘어감 or
	//채팅 새 페이지로 넘어감
	Integer getChattingRoomId(String login_email, String other_email) throws Exception;
	
	//채팅방 만들기
	//채팅 새 페이지에서 채팅 보내기를 누르면 생성됨
	int makeChattingRoom(String login_email, String other_email, String message) throws Exception;
	
	//채팅방 목록 불러오기
	List<ChatRoomDTO> showChatRoomList(String login_email) throws Exception;
	
	//채팅 목록 불러오기
	List<ChattingDTO> readChatting(Integer chatroom_id, String login_email) throws Exception;
	
	//기존 채팅방에서 채팅 보내기
	void sendChatting(Integer chatroom_id, String login_email, String message) throws Exception;
	
	//채팅방 나가기
	void deleteChattingRoom(Integer chatroom_id, String login_email) throws Exception;
	
}
