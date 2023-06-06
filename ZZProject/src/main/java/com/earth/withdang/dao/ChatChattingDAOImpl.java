package com.earth.withdang.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.withdang.domain.ChatChattingDTO;


@Repository
public class ChatChattingDAOImpl implements ChatChattingDAO {
	
	private SqlSession session;
	private static String namespace = "com.earth.withdang.dao.ChatChattingMapper.";
	
	public ChatChattingDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<ChatChattingDTO> selectAll(Integer chatroom_id) throws Exception {
		return session.selectList(namespace + "selectAll", chatroom_id);
	}

	@Override
	public int insert(ChatChattingDTO chatChattingDTO) throws Exception {
		return session.insert(namespace + "insert", chatChattingDTO);
	}

	@Override
	public int updateCheckReadToTrue(Integer chatroom_id, String login_nickname) throws Exception {
		Map map = new HashMap();
		map.put("chatroom_id", chatroom_id);
		map.put("login_nickname", login_nickname);
		return session.update(namespace + "updateCheckReadToTrue", map);
	}
	
}
