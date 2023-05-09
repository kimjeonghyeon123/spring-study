package com.earth.jeonghyeonkim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earth.jeonghyeonkim.dao.DanggeunDAO;
import com.earth.jeonghyeonkim.domain.DanggeunDTO;

@Service
public class DanggeunServiceImpl implements DanggeunService {

	@Autowired
	private DanggeunDAO danggeunDAO;

	@Override
	public DanggeunDTO read(Integer id) throws Exception {
		DanggeunDTO danggeunDTO = danggeunDAO.select(id);
		danggeunDAO.addViewCnt(id);
		return danggeunDTO;
	}

	@Override
	public List<DanggeunDTO> readAll() throws Exception {
		return danggeunDAO.selectAll();
	}
	
	@Override
	public List<DanggeunDTO> searchItems(Integer type_id) throws Exception {
		return danggeunDAO.selectByType(type_id);
	}

	@Override
	public int register(DanggeunDTO danggeunDTO) throws Exception {
		return danggeunDAO.insert(danggeunDTO);
	}

	@Override
	public int storeProduct(Integer id, String writer) throws Exception {
		return danggeunDAO.insertStore(id, writer);
	}

	@Override
	public int addCount(Integer id) throws Exception {
		return danggeunDAO.addAddCnt(id);
	}
	
}
