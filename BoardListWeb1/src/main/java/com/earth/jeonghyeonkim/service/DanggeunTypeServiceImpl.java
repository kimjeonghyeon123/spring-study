package com.earth.jeonghyeonkim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earth.jeonghyeonkim.dao.DanggeunTypeDAO;
import com.earth.jeonghyeonkim.domain.DanggeunTypeDTO;

@Service
public class DanggeunTypeServiceImpl implements DanggeunTypeService{

	@Autowired
	DanggeunTypeDAO danggeunTypeDAO;

	@Override
	public List<DanggeunTypeDTO> getTypeList() throws Exception {
		return danggeunTypeDAO.selectAll();
	}
	
}
