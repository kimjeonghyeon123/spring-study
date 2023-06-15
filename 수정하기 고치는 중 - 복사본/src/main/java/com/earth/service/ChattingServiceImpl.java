package com.earth.service;

import java.sql.Timestamp;
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
		return chatUserChatRoomMapper.selectUserCnt(chatroom_id);
	}
	
	@Override
	public ChatUserChatRoomDTO getChattingRoom(String login_nickname, String other_nickname) throws Exception {
		Map map = new HashMap();
		map.put("login_nickname", login_nickname);
		map.put("other_nickname", other_nickname);
		return chatUserChatRoomMapper.selectChatRoom(map);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int makeChattingRoom(String login_nickname, String other_nickname, String message, Timestamp timestamp) throws Exception {
		ChatChatRoomDTO chatChatRoomDTO = new ChatChatRoomDTO(login_nickname, message, timestamp);
		
		//채팅방 생성
		chatChatRoomMapper.insert(chatChatRoomDTO);
		//채팅 생성
		chatChattingMapper.insert(new ChatChattingDTO(login_nickname, chatChatRoomDTO.getId(), message, timestamp));
		//채팅방정보 생성
		chatUserChatRoomMapper.insert(new ChatUserChatRoomDTO(chatChatRoomDTO.getId(), login_nickname, other_nickname, true, timestamp));
		
		return chatChatRoomDTO.getId();
	}

	@Override
	public List<ChatChatRoomDTO> showChatRoomList(String login_nickname) throws Exception {
		return chatChatRoomMapper.selectAll(login_nickname);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<ChatChattingDTO> readChatting(Integer chatroom_id, String login_nickname, String other_nickname) throws Exception {
		Map map = new HashMap();

		map.put("chatroom_id", chatroom_id);
		map.put("login_nickname", login_nickname);
		map.put("other_nickname", other_nickname);
		Timestamp participation_date = chatUserChatRoomMapper.selectChatRoom(map).getParticipation_date();
		
		map.put("participation_date", participation_date);

		List<ChatChattingDTO> list = chatChattingMapper.selectAll(map);

		chatChattingMapper.updateCheckReadToTrue(map);
		chatChatRoomMapper.updateUnreadCntToZero(map);
		
		return list;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void sendChatting(Integer chatroom_id, String login_nickname, String other_nickname, String message, Timestamp timestamp) throws Exception {
		Map map = new HashMap();
		map.put("chatroom_id", chatroom_id);
		map.put("login_nickname", login_nickname);
		map.put("other_nickname", other_nickname);
		map.put("participation_date", timestamp);
		
		ChatUserChatRoomDTO chatUserChatRoomDTO = chatUserChatRoomMapper.selectChatRoom(map);
		
		Map map1 = new HashMap();
		map1.put("chatroom_id", chatroom_id);
		map1.put("login_nickname", other_nickname);
		map1.put("other_nickname", login_nickname);
		map1.put("participation_date", timestamp);
		
		if(chatUserChatRoomMapper.selectUserCnt(chatroom_id) != 2){
			//내가 방에서 나간 상태
			if(!chatUserChatRoomDTO.getParticipation()) {
				chatUserChatRoomMapper.entryChatRoom(map);
			}
			//상대방이 방에서 나간 상태
			else {
				chatUserChatRoomMapper.entryChatRoom(map1);
			}
		}
		chatChatRoomMapper.updateChatRoom(new ChatChatRoomDTO(chatroom_id, login_nickname, message, timestamp));
		chatChattingMapper.insert(new ChatChattingDTO(login_nickname, chatroom_id, message, timestamp));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteChattingRoom(Integer chatroom_id, String login_nickname) throws Exception {
		int cnt = chatUserChatRoomMapper.selectUserCnt(chatroom_id);
		
		if(cnt == 2) {
			Map map = new HashMap();
			map.put("chatroom_id", chatroom_id);
			map.put("login_nickname", login_nickname);
			chatUserChatRoomMapper.leaveChatRoom(map);
		}
		else {
			chatChatRoomMapper.delete(chatroom_id);
		}
	}

	@Override
	public int countUnreadChat(String login_nickname) throws Exception {
		return chatChatRoomMapper.countUnreadChatting(login_nickname);
	}
	
}
