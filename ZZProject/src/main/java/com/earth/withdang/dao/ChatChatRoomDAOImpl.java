package com.earth.withdang.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.withdang.domain.ChatChatRoomDTO;

@Repository
public class ChatChatRoomDAOImpl implements ChatChatRoomDAO {

	private SqlSession session;
	private static String namespace = "com.earth.withdang.dao.ChatChatRoomMapper.";
	
	public ChatChatRoomDAOImpl(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<ChatChatRoomDTO> selectAll(String login_nickname) throws Exception {
		return session.selectList(namespace + "selectAll", login_nickname);
	}
	
	@Override
	public int insert(ChatChatRoomDTO chatChatRoomDTO) throws Exception {
		session.insert(namespace + "insert", chatChatRoomDTO);
		return chatChatRoomDTO.getId();
	}

	@Override
	public int updateChatRoom(ChatChatRoomDTO chatChatRoomDTO) throws Exception {
		return session.update(namespace + "updateChatRoom", chatChatRoomDTO);
	}

	@Override
	public int updateUnreadCntToZero(Integer id, String login_nickname) throws Exception {
		Map map = new HashMap();
		map.put("id", id);
		map.put("login_nickname", login_nickname);
		return session.update(namespace + "updateUnreadCntToZero", map);
	}

	@Override
	public int delete(Integer id) throws Exception {
		return session.delete(namespace + "delete", id);
	}
	
}
