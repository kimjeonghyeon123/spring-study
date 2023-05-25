package com.earth.korea.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.korea.domain.ChatRoomDTO;

@Repository
public class ChatRoomDaoImpl implements ChatRoomDao {

	private SqlSession session;
	private static String namespace = "com.earth.korea.dao.ChatRoomMapper.";
	
	public ChatRoomDaoImpl(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<ChatRoomDTO> selectAll(String login_id) throws Exception {
		return session.selectList(namespace + "selectAll", login_id);
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
	public int updateUnreadCntToZero(Integer id, String login_id) throws Exception {
		Map map = new HashMap();
		map.put("id", id);
		map.put("login_id", login_id);
		return session.update(namespace + "updateUnreadCntToZero", map);
	}

}
