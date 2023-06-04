package com.earth.danggeun.dao;

import com.earth.danggeun.domain.UserChatRoomDTO;

public interface UserChatRoomDao {
 
	//상대가 채팅방 삭제했는지 확인
	int selectChatRoomCnt(Integer chatroom_id) throws Exception;
	
	//채팅방번호 가져오기
	Integer selectChatRoomId(String login_email, String other_email) throws Exception;

	//채팅방 생성하기
	int insert(UserChatRoomDTO userChatRoomDTO) throws Exception; 
	
	//채팅방 삭제하기
	int delete(Integer chatroom_id, String login_email) throws Exception;
	
}
