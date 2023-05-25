package com.earth.korea;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.earth.korea.dao.ChatRoomDao;
import com.earth.korea.dao.ChattingDao;
import com.earth.korea.dao.UserChatRoomDao;
import com.earth.korea.domain.ChatRoomDTO;
import com.earth.korea.domain.ChattingDTO;
import com.earth.korea.domain.UserChatRoomDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class SampleTest {

	@Autowired
	ChatRoomDao chatRoomDao;
	@Autowired
	UserChatRoomDao userChatRoomDao;
	@Autowired
	ChattingDao chattingDao;
	
	@Test//내 아이디, 상대아이디, 내용
	public void insertTest() throws Exception {
		ChatRoomDTO chatRoomDTO = new ChatRoomDTO();
		int idx = chatRoomDao.insert(chatRoomDTO);
		UserChatRoomDTO userChatRoomDTO = new UserChatRoomDTO();
		userChatRoomDTO.setChatroom_id(idx);
		userChatRoomDTO.setLogin_id("user1");
		userChatRoomDTO.setOther_id("user2");
		userChatRoomDao.insert(userChatRoomDTO);
		userChatRoomDTO.setLogin_id("user2");
		userChatRoomDTO.setOther_id("user1");
		userChatRoomDao.insert(userChatRoomDTO);
		ChattingDTO chattingDTO = new ChattingDTO();
		chattingDTO.setSender_id("user1");
		chattingDTO.setChatroom_id(idx);
		chattingDTO.setChat("hi");
		chattingDao.insert(chattingDTO);
	}
	
	
}
