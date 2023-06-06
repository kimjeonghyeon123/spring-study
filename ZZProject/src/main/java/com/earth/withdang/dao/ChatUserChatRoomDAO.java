package com.earth.withdang.dao;

import com.earth.withdang.domain.ChatUserChatRoomDTO;

public interface ChatUserChatRoomDAO {
	
	//상대가 채팅방 삭제했는지 확인
	int selectChatRoomCnt(Integer chatroom_id) throws Exception;
	
	//채팅방번호 가져오기
	Integer selectChatRoomId(String login_nickname, String other_nickname) throws Exception;

	//채팅방 생성하기
	int insert(ChatUserChatRoomDTO chatUserChatRoomDTO) throws Exception; 
	
	//채팅방 삭제하기
	int delete(Integer chatroom_id, String login_nickname) throws Exception;
	
}
