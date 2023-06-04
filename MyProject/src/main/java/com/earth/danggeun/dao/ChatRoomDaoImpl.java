package com.earth.danggeun.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.danggeun.domain.ChatRoomDTO;

@Repository
public class ChatRoomDaoImpl implements ChatRoomDao {

	private SqlSession session;
	private static String namespace = "com.earth.danggeun.dao.ChatRoomMapper.";
	
	public ChatRoomDaoImpl(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<ChatRoomDTO> selectAll(String login_email) throws Exception {
		return session.selectList(namespace + "selectAll", login_email);
	}
	
	@Override
	public int insert(ChatRoomDTO chatRoomDTO) throws Exception {
		session.insert(namespace + "insert", chatRoomDTO);
		return chatRoomDTO.getId();
	}

	@Override
	public int updateChatRoom(ChatRoomDTO chatRoomDTO) throws Exception {
		return session.update(namespace + "updateChatRoom", chatRoomDTO);
	}

	@Override
	public int updateUnreadCntToZero(Integer id, String login_email) throws Exception {
		Map map = new HashMap();
		map.put("id", id);
		map.put("login_email", login_email);
		return session.update(namespace + "updateUnreadCntToZero", map);
	}

	@Override
	public int delete(Integer id) throws Exception {
		return session.delete(namespace + "delete", id);
	}

}
