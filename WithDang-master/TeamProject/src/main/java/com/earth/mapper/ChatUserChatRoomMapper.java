package com.earth.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.earth.domain.ChatUserChatRoomDTO;

@Mapper
public interface ChatUserChatRoomMapper {
	
	//현재 채팅방 이용자 수 가져오기
	int selectUserCnt(Integer chatroom_id) throws Exception;
	
	//채팅방번호 가져오기
	ChatUserChatRoomDTO selectChatRoom(Map map) throws Exception;

	//채팅방 생성하기
	int insert(ChatUserChatRoomDTO chatUserChatRoomDTO) throws Exception; 
	
	//채팅방 나갈 때
	int leaveChatRoom(Map map) throws Exception;
	
	//채팅방 다시 들어올 때
	int entryChatRoom(Map map) throws Exception;
	
}
