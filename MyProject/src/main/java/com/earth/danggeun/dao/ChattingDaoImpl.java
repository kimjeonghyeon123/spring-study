package com.earth.danggeun.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.danggeun.domain.ChattingDTO;

@Repository
public class ChattingDaoImpl implements ChattingDao {
	
	private SqlSession session;
	private static String namespace = "com.earth.danggeun.dao.ChattingMapper.";
	
	public ChattingDaoImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<ChattingDTO> selectAll(Integer chatroom_id) throws Exception {
		return session.selectList(namespace + "selectAll", chatroom_id);
	}

	@Override
	public int insert(ChattingDTO chattingDTO) throws Exception {
		return session.insert(namespace + "insert", chattingDTO);
	}
	
	@Override
	public int updateCheckReadToTrue(Integer chatroom_id, String login_email) throws Exception {
		Map map = new HashMap();
		map.put("chatroom_id", chatroom_id);
		map.put("login_email", login_email);
		return session.update(namespace + "updateCheckReadToTrue", map);
	}

	@Override
	public int deleteAll(Integer chatroom_id) throws Exception {
		return session.delete(namespace + "deleteAll", chatroom_id);
	}

}
