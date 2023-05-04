package com.earth.jeonghyeonkim.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earth.jeonghyeonkim.dao.DangBoardDAO;
import com.earth.jeonghyeonkim.domain.DangBoardDTO;
import com.earth.jeonghyeonkim.domain.SearchItem;

@Service
public class DangBoardServiceImpl implements DangBoardService {
	
	@Autowired
	DangBoardDAO dangBoardDAO;

	@Override
	public List<DangBoardDTO> getPage(Map map) throws Exception {
		return dangBoardDAO.selectPage(map);
	}

	@Override
	public int getCount() throws Exception {
		return dangBoardDAO.count();
	}

	@Override
	public DangBoardDTO read(Integer bno) throws Exception {
		DangBoardDTO dangBoardDTO = dangBoardDAO.select(bno);
		dangBoardDAO.increaseViewCnt(bno);
		return dangBoardDTO;
	}

	@Override
	public int getSearchResultCnt(SearchItem sc) throws Exception {
		return dangBoardDAO.searchResultCnt(sc);
	}

	@Override
	public List<DangBoardDTO> getSearchSelectPage(SearchItem sc) throws Exception {
		return dangBoardDAO.searchSelectPage(sc);
	}

	@Override
	public int write(DangBoardDTO dangBoardDTO) throws Exception {
		return dangBoardDAO.insert(dangBoardDTO);
	}
	
	
}
