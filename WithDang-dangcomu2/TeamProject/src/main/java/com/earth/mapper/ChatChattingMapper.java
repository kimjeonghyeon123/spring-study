package com.earth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.earth.domain.ChatChattingDTO;

@Mapper
public interface ChatChattingMapper {
	
	//채팅방 가져오기
	List<ChatChattingDTO> selectAll(Integer chatroom_id) throws Exception;
	
	//채팅 보내기
	int insert(ChatChattingDTO chatChattingDTO) throws Exception;
	
	//채팅 읽음 상태로 변경하기
	int updateCheckReadToTrue(Map map) throws Exception;
	
}
