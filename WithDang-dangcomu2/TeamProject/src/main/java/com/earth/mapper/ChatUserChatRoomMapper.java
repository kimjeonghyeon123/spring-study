package com.earth.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.earth.domain.ChatUserChatRoomDTO;

@Mapper
public interface ChatUserChatRoomMapper {
	
	//상대가 채팅방 삭제했는지 확인
	int selectChatRoomCnt(Integer chatroom_id) throws Exception;
	
	//채팅방번호 가져오기
	Integer selectChatRoomId(Map map) throws Exception;

	//채팅방 생성하기
	int insert(ChatUserChatRoomDTO chatUserChatRoomDTO) throws Exception; 
	
	//채팅방 삭제하기
	int delete(Map map) throws Exception;
	
}
