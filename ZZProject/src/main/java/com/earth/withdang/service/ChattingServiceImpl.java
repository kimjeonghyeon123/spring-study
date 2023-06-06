package com.earth.withdang.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earth.withdang.dao.ChatChatRoomDAO;
import com.earth.withdang.dao.ChatChattingDAO;
import com.earth.withdang.dao.ChatUserChatRoomDAO;
import com.earth.withdang.domain.ChatChatRoomDTO;
import com.earth.withdang.domain.ChatChattingDTO;
import com.earth.withdang.domain.ChatUserChatRoomDTO;

@Service
public class ChattingServiceImpl implements ChattingService {

	private ChatUserChatRoomDAO chatUserChatRoomDAO;
	private ChatChatRoomDAO chatChatRoomDAO;
	private ChatChattingDAO chatChattingDAO;
	
	public ChattingServiceImpl(ChatUserChatRoomDAO chatUserChatRoomDAO, ChatChatRoomDAO chatChatRoomDAO,
			ChatChattingDAO chatChattingDAO) {
		this.chatUserChatRoomDAO = chatUserChatRoomDAO;
		this.chatChatRoomDAO = chatChatRoomDAO;
		this.chatChattingDAO = chatChattingDAO;
	}

	@Override
	public int getChatRoomCnt(Integer chatroom_id) throws Exception {
		return chatUserChatRoomDAO.selectChatRoomCnt(chatroom_id);
	}
	
	@Override
	public Integer getChattingRoomId(String login_nickname, String other_nickname) throws Exception {
		return chatUserChatRoomDAO.selectChatRoomId(login_nickname, other_nickname);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int makeChattingRoom(String login_nickname, String other_nickname, String message) throws Exception {
		//채팅방 생성
		int chatroomId = chatChatRoomDAO.insert(new ChatChatRoomDTO(login_nickname, message));
		//채팅 생성
		chatChattingDAO.insert(new ChatChattingDTO(login_nickname, chatroomId, message));
		//채팅방정보 생성
		chatUserChatRoomDAO.insert(new ChatUserChatRoomDTO(chatroomId, login_nickname, other_nickname));
		
		return chatroomId;
	}

	@Override
	public List<ChatChatRoomDTO> showChatRoomList(String login_nickname) throws Exception {
		return chatChatRoomDAO.selectAll(login_nickname);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<ChatChattingDTO> readChatting(Integer chatroom_id, String login_nickname) throws Exception {
		List<ChatChattingDTO> list = chatChattingDAO.selectAll(chatroom_id);
		chatChattingDAO.updateCheckReadToTrue(chatroom_id, login_nickname);
		chatChatRoomDAO.updateUnreadCntToZero(chatroom_id, login_nickname);
		
		return list;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void sendChatting(Integer chatroom_id, String login_nickname, String message) throws Exception {
		chatChattingDAO.insert(new ChatChattingDTO(login_nickname, chatroom_id, message));
		chatChatRoomDAO.updateChatRoom(new ChatChatRoomDTO(chatroom_id, login_nickname, message));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteChattingRoom(Integer chatroom_id, String login_email) throws Exception {
		int cnt = chatUserChatRoomDAO.selectChatRoomCnt(chatroom_id);
		
		if(cnt == 2) {
			chatUserChatRoomDAO.delete(chatroom_id, login_email);
		}
		else {
			chatChatRoomDAO.delete(chatroom_id);
		}
	}
	
}
