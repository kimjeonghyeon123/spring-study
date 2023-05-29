package com.earth.korea.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.earth.korea.domain.ChattingDTO;

@Repository
public class ChattingDaoImpl implements ChattingDao {
	
	private SqlSession session;
	private static String namespace = "com.earth.korea.dao.ChattingMapper.";
	
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
	public int updateCheckReadToTrue(Integer chatroom_id, String login_id) throws Exception {
		Map map = new HashMap();
		map.put("chatroom_id", chatroom_id);
		map.put("login_id", login_id);
		return session.update(namespace + "updateCheckReadToTrue", map);
	}

	@Override
	public int deleteAll(Integer chatroom_id) throws Exception {
		return session.delete(namespace + "deleteAll", chatroom_id);
	}

}
