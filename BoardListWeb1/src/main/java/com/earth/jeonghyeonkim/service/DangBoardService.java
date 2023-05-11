package com.earth.jeonghyeonkim.service;

import java.util.List;
import java.util.Map;

import com.earth.jeonghyeonkim.domain.DangBoardDTO;
import com.earth.jeonghyeonkim.domain.SearchItem;

public interface DangBoardService {

	int getCount() throws Exception;
	DangBoardDTO read(Integer bno) throws Exception;
	List<DangBoardDTO> getPage(Map map) throws Exception;
	int	getSearchResultCnt(SearchItem sc) throws Exception;
	List<DangBoardDTO> getSearchSelectPage(SearchItem sc) throws Exception;
	int remove(Integer bno, String writer) throws Exception;
	int write(DangBoardDTO dangBoardDTO) throws Exception;
	int modify(DangBoardDTO dangBoardDTO) throws Exception;
}