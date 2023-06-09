package com.earth.danggeun.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earth.danggeun.dao.ChatRoomDao;
import com.earth.danggeun.dao.ChattingDao;
import com.earth.danggeun.dao.UserChatRoomDao;
import com.earth.danggeun.domain.ChatRoomDTO;
import com.earth.danggeun.domain.ChattingDTO;
import com.earth.danggeun.domain.UserChatRoomDTO;

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
	public int getChatRoomCnt(Integer chatroom_id) throws Exception {
		return userChatRoomDao.selectChatRoomCnt(chatroom_id);
	}
	
	@Override
	public Integer getChattingRoomId(String login_email, String other_email) throws Exception {
		return userChatRoomDao.selectChatRoomId(login_email, other_email);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int makeChattingRoom(String login_email, String other_email, String message) throws Exception {
		//채팅방 생성
		int chatroomId = chatRoomDao.insert(new ChatRoomDTO(login_email, message));
		//채팅 생성
		chattingDao.insert(new ChattingDTO(login_email, chatroomId, message));
		//채팅방정보 생성
		userChatRoomDao.insert(new UserChatRoomDTO(chatroomId, login_email, other_email));
		
		return chatroomId;
	}

	@Override
	public List<ChatRoomDTO> showChatRoomList(String login_email) throws Exception {
		return chatRoomDao.selectAll(login_email);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<ChattingDTO> readChatting(Integer chatroom_id, String login_email) throws Exception {
		List<ChattingDTO> list = chattingDao.selectAll(chatroom_id);
		chattingDao.updateCheckReadToTrue(chatroom_id, login_email);
		chatRoomDao.updateUnreadCntToZero(chatroom_id, login_email);
		
		return list;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void sendChatting(Integer chatroom_id, String login_email, String message) throws Exception {
		chattingDao.insert(new ChattingDTO(login_email, chatroom_id, message));
		chatRoomDao.updateChatRoom(new ChatRoomDTO(chatroom_id, login_email, message));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteChattingRoom(Integer chatroom_id, String login_email) throws Exception {
		int cnt = userChatRoomDao.selectChatRoomCnt(chatroom_id);
		
		if(cnt == 2) {
			userChatRoomDao.delete(chatroom_id, login_email);
		}
		else {
			userChatRoomDao.delete(chatroom_id, login_email);
			chatRoomDao.delete(chatroom_id);
			chattingDao.deleteAll(chatroom_id);
		}
	}
	
}
