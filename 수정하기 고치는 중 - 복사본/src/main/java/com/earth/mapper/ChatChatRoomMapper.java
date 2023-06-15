package com.earth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.earth.domain.ChatChatRoomDTO;

@Mapper
public interface ChatChatRoomMapper {
	
	//모든 채팅방 목록 가져오기
	List<ChatChatRoomDTO> selectAll(String login_nickname) throws Exception;
	
	//안읽은 채팅 개수 세기
	int countUnreadChatting(String login_nickname) throws Exception;
	
	//채팅방 만들기
	int insert(ChatChatRoomDTO chatChatRoomDTO) throws Exception;
	
	//채팅방 최신메시지 업데이트하기
	int updateChatRoom(ChatChatRoomDTO chatChatRoomDTO) throws Exception;
	
	//채팅방 읽은 상태로 만들기
	int updateUnreadCntToZero(Map map) throws Exception;
	
	//채팅방 삭제할 때 사용
	int delete(Integer id) throws Exception;
	
}
