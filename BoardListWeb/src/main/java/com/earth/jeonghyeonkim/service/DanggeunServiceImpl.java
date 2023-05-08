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
		return danggeunDAO.select(id);
	}

	@Override
	public List<DanggeunDTO> readAll() throws Exception {
		return danggeunDAO.selectAll();
	}
	
	@Override
	public List<DanggeunDTO> searchItems(Integer type_id, Integer local_id) throws Exception {
		return danggeunDAO.selectBySearch(type_id, local_id);
	}

	@Override
	public int register(DanggeunDTO danggeunDTO) throws Exception {
		return danggeunDAO.insert(danggeunDTO);
	}	
	
}
