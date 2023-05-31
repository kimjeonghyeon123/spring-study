package com.earth.korea.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.korea.domain.UserChatRoomDTO;

@Repository
public class UserChatRoomDaoImpl implements UserChatRoomDao {

	private SqlSession session;
	private static String namespace = "com.earth.korea.dao.UserChatRoomMapper.";
	
	public UserChatRoomDaoImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public int selectChatRoomCnt(Integer chatroom_id) throws Exception {
		return session.selectOne(namespace + "selectChatRoomCnt", chatroom_id);
	}
	
	@Override
	public Integer selectChatRoomId(String login_id, String other_id) throws Exception {
		Map map = new HashMap();
		map.put("login_id", login_id);
		map.put("other_id", other_id);
		return session.selectOne(namespace + "selectChatRoomId", map);
	}
	
	@Override
	public int insert(UserChatRoomDTO userChatRoomDTO) throws Exception {
		return session.insert(namespace + "insert", userChatRoomDTO);
	}

	@Override
	public int delete(Integer chatroom_id, String login_id) throws Exception {
		Map map = new HashMap();
		map.put("chatroom_id", chatroom_id);
		map.put("login_id", login_id);
		return session.delete(namespace + "delete", map);
	}

}
