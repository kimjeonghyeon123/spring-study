package com.earth.jeonghyeonkim.dao;

import java.util.List;
import java.util.Map;

import com.earth.jeonghyeonkim.domain.DangBoardDTO;
import com.earth.jeonghyeonkim.domain.SearchItem;

public interface DangBoardDAO {
	
	DangBoardDTO select(Integer bno) throws Exception;
	List<DangBoardDTO> selectPage(Map map) throws Exception;
	List<DangBoardDTO> selectAll() throws Exception;
	int	increaseViewCnt(Integer bno) throws Exception;
	int	count() throws Exception;
	int	searchResultCnt(SearchItem sc) throws Exception;
	List<DangBoardDTO> searchSelectPage(SearchItem sc) throws Exception;
	int insert(DangBoardDTO dangBoardDTO) throws Exception;
	
}
