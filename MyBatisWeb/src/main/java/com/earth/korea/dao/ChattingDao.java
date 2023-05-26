package com.earth.korea.dao;

import java.util.List;

import com.earth.korea.domain.ChattingDTO;

public interface ChattingDao {

	//채팅방 가져오기
	List<ChattingDTO> selectAll(Integer chatroom_id) throws Exception;
	
	//채팅 보내기
	int insert(ChattingDTO chattingDTO) throws Exception;
	
	//채팅 읽음 상태로 변경하기
	int updateCheckReadToTrue(Integer chatroom_id, String login_id) throws Exception;
	
}
