package com.earth.danggeun.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.danggeun.domain.UserChatRoomDTO;

@Repository
public class UserChatRoomDaoImpl implements UserChatRoomDao {

	private SqlSession session;
	private static String namespace = "com.earth.danggeun.dao.UserChatRoomMapper.";
	
	public UserChatRoomDaoImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public int selectChatRoomCnt(Integer chatroom_id) throws Exception {
		return session.selectOne(namespace + "selectChatRoomCnt", chatroom_id);
	}
	
	@Override
	public Integer selectChatRoomId(String login_email, String other_email) throws Exception {
		Map map = new HashMap();
		map.put("login_email", login_email);
		map.put("other_email", other_email);
		return session.selectOne(namespace + "selectChatRoomId", map);
	}
	
	@Override
	public int insert(UserChatRoomDTO userChatRoomDTO) throws Exception {
		return session.insert(namespace + "insert", userChatRoomDTO);
	}

	@Override
	public int delete(Integer chatroom_id, String login_email) throws Exception {
		Map map = new HashMap();
		map.put("chatroom_id", chatroom_id);
		map.put("login_email", login_email);
		return session.delete(namespace + "delete", map);
	}

}
