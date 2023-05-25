package com.earth.korea.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.earth.korea.domain.ChatDTO;

@Repository
public class ChatDaoImpl implements ChatDao {
	
	@Autowired
	private SqlSession session;
	private static String namespace = "com.earth.korea.dao.ChatMapper.";
	
	@Override
	public List<ChatDTO> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}
	
	@Override
	public int insert(ChatDTO chatDTO) throws Exception {
		return session.insert(namespace + "insert", chatDTO);
	}

	
	
}
