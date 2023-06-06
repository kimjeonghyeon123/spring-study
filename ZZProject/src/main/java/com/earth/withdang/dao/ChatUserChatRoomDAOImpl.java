package com.earth.withdang.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.withdang.domain.ChatUserChatRoomDTO;

@Repository
public class ChatUserChatRoomDAOImpl implements ChatUserChatRoomDAO {

	private SqlSession session;
	private static String namespace = "com.earth.withdang.dao.ChatUserChatRoomMapper.";
	
	public ChatUserChatRoomDAOImpl(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public int selectChatRoomCnt(Integer chatroom_id) throws Exception {
		return session.selectOne(namespace + "selectChatRoomCnt", chatroom_id);
	}

	@Override
	public Integer selectChatRoomId(String login_nickname, String other_nickname) throws Exception {
		Map map = new HashMap();
		map.put("login_nickname", login_nickname);
		map.put("other_nickname", other_nickname);
		return session.selectOne(namespace + "selectChatRoomId", map);
	}

	@Override
	public int insert(ChatUserChatRoomDTO chatUserChatRoomDTO) throws Exception {
		return session.insert(namespace + "insert", chatUserChatRoomDTO);
	}

	@Override
	public int delete(Integer chatroom_id, String login_nickname) throws Exception {
		Map map = new HashMap();
		map.put("chatroom_id", chatroom_id);
		map.put("login_nickname", login_nickname);
		return session.delete(namespace + "delete", map);
	}

}
