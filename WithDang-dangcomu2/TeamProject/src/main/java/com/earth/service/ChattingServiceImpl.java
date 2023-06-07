package com.earth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earth.mapper.ChatChatRoomMapper;
import com.earth.mapper.ChatChattingMapper;
import com.earth.mapper.ChatUserChatRoomMapper;
import com.earth.domain.ChatChatRoomDTO;
import com.earth.domain.ChatChattingDTO;
import com.earth.domain.ChatUserChatRoomDTO;

@Service
public class ChattingServiceImpl implements ChattingService {

	private ChatUserChatRoomMapper chatUserChatRoomMapper;
	private ChatChatRoomMapper chatChatRoomMapper;
	private ChatChattingMapper chatChattingMapper;
	
	public ChattingServiceImpl(ChatUserChatRoomMapper chatUserChatRoomMapper, ChatChatRoomMapper chatChatRoomMapper,
			ChatChattingMapper chatChattingMapper) {
		this.chatUserChatRoomMapper = chatUserChatRoomMapper;
		this.chatChatRoomMapper = chatChatRoomMapper;
		this.chatChattingMapper = chatChattingMapper;
	}

	@Override
	public int getChatRoomCnt(Integer chatroom_id) throws Exception {
		return chatUserChatRoomMapper.selectChatRoomCnt(chatroom_id);
	}
	
	@Override
	public Integer getChattingRoomId(String login_nickname, String other_nickname) throws Exception {
		Map map = new HashMap();
		map.put("login_nickname", login_nickname);
		map.put("other_nickname", other_nickname);
		return chatUserChatRoomMapper.selectChatRoomId(map);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int makeChattingRoom(String login_nickname, String other_nickname, String message) throws Exception {
		//채팅방 생성
		int chatroomId = chatChatRoomMapper.insert(new ChatChatRoomDTO(login_nickname, message));
		//채팅 생성
		chatChattingMapper.insert(new ChatChattingDTO(login_nickname, chatroomId, message));
		//채팅방정보 생성
		chatUserChatRoomMapper.insert(new ChatUserChatRoomDTO(chatroomId, login_nickname, other_nickname));
		
		return chatroomId;
	}

	@Override
	public List<ChatChatRoomDTO> showChatRoomList(String login_nickname) throws Exception {
		return chatChatRoomMapper.selectAll(login_nickname);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<ChatChattingDTO> readChatting(Integer chatroom_id, String login_nickname) throws Exception {
		List<ChatChattingDTO> list = chatChattingMapper.selectAll(chatroom_id);
		Map map = new HashMap();
		map.put("chatroom_id", chatroom_id);
		map.put("login_nickname", login_nickname);
		chatChattingMapper.updateCheckReadToTrue(map);
		chatChatRoomMapper.updateUnreadCntToZero(map);
		
		return list;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void sendChatting(Integer chatroom_id, String login_nickname, String message) throws Exception {
		chatChattingMapper.insert(new ChatChattingDTO(login_nickname, chatroom_id, message));
		chatChatRoomMapper.updateChatRoom(new ChatChatRoomDTO(chatroom_id, login_nickname, message));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteChattingRoom(Integer chatroom_id, String login_nickname) throws Exception {
		int cnt = chatUserChatRoomMapper.selectChatRoomCnt(chatroom_id);
		
		if(cnt == 2) {
			Map map = new HashMap();
			map.put("chatroom_id", chatroom_id);
			map.put("login_nickname", login_nickname);
			chatUserChatRoomMapper.delete(map);
		}
		else {
			chatChatRoomMapper.delete(chatroom_id);
		}
	}
	
}
