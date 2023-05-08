package com.earth.jeonghyeonkim.dao;

import java.util.List;
import java.util.Map;

import com.earth.jeonghyeonkim.domain.DangBoardDTO;
import com.earth.jeonghyeonkim.domain.SearchItem;

public interface DangBoardDAO {
	
	int count() throws Exception;
	DangBoardDTO select(Integer bno) throws Exception;
	List<DangBoardDTO> selectAll() throws Exception;
	List<DangBoardDTO> selectPage(Map map) throws Exception;
	int searchResultCnt(SearchItem sc) throws Exception;
	List<DangBoardDTO> searchSelectPage(SearchItem sc) throws Exception;
	int deleteAll() throws Exception;
	int delete(Integer bno, String writer) throws Exception;
	int insert(DangBoardDTO dangBoardDTO) throws Exception;
	int update(DangBoardDTO dangBoardDTO) throws Exception;
	int increaseViewCnt(Integer bno) throws Exception;
	
}
