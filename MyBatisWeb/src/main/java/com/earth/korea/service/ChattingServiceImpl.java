package com.earth.korea.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earth.korea.dao.ChatRoomDao;
import com.earth.korea.dao.ChattingDao;
import com.earth.korea.dao.UserChatRoomDao;
import com.earth.korea.domain.ChatRoomDTO;
import com.earth.korea.domain.ChattingDTO;
import com.earth.korea.domain.UserChatRoomDTO;

@Service
public class ChattingServiceImpl implements ChattingService {

	private UserChatRoomDao userChatRoomDao;
	private ChatRoomDao chatRoomDao;
	private ChattingDao chattingDao;
	
	public ChattingServiceImpl(UserChatRoomDao userChatRoomDao, ChatRoomDao chatRoomDao, ChattingDao chattingDao) {
		this.userChatRoomDao = userChatRoomDao;
		this.chatRoomDao = chatRoomDao;
		this.chattingDao = chattingDao;
	}

	@Override
	public Integer getChattingRoomId(String login_id, String other_id) throws Exception {
		return userChatRoomDao.selectChatRoomId(login_id, other_id);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int makeChattingRoom(String login_id, String other_id, String message) throws Exception {
		//채팅방 생성
		int chatroomId = chatRoomDao.insert(new ChatRoomDTO(login_id, message));
		//채팅 생성
		chattingDao.insert(new ChattingDTO(login_id, chatroomId, message));
		//채팅방정보 생성
		userChatRoomDao.insert(new UserChatRoomDTO(chatroomId, login_id, other_id));
		
		return chatroomId;
	}

	@Override
	public List<ChatRoomDTO> showChatRoomList(String login_id) throws Exception {
		return chatRoomDao.selectAll(login_id);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<ChattingDTO> readChatting(Integer chatroom_id, String login_id) throws Exception {
		List<ChattingDTO> list = chattingDao.selectAll(chatroom_id);
		chattingDao.updateCheckReadToTrue(chatroom_id, login_id);
		chatRoomDao.updateUnreadCntToZero(chatroom_id, login_id);
		
		return list;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void sendChatting(Integer chatrrom_id, String login_id, String message) throws Exception {
		chattingDao.insert(new ChattingDTO(login_id, chatrrom_id, message));
		chatRoomDao.updateChatRoom(new ChatRoomDTO(chatrrom_id, login_id, message));
	}
	
}
