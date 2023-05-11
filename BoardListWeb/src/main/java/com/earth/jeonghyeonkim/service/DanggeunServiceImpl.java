package com.earth.jeonghyeonkim.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earth.jeonghyeonkim.dao.DangMemberDAO;
import com.earth.jeonghyeonkim.dao.DanggeunDAO;
import com.earth.jeonghyeonkim.dao.ZzimDanggeunDAO;
import com.earth.jeonghyeonkim.domain.DanggeunDTO;
import com.earth.jeonghyeonkim.domain.ZzimDanggeunDTO;

@Service
public class DanggeunServiceImpl implements DanggeunService {

	@Autowired
	private DanggeunDAO danggeunDAO;
	@Autowired
	private ZzimDanggeunDAO zzimDanggeunDAO;
	@Autowired
	private DangMemberDAO dangMemberDAO;
	
	@Override
	public List<DanggeunDTO> readDanggeunListByOption(Integer type_id, String login_email) throws Exception {

	    Set<Integer> zzimIdSet = new HashSet<>(zzimDanggeunDAO.selectByMemberEmail(login_email));
	    List<DanggeunDTO> danggeunList = null;

	    if(type_id == 0) {
	        danggeunList = danggeunDAO.selectAll();
	    }
	    else {
	        danggeunList = danggeunDAO.selectByOption(type_id);
	    }

	    for(DanggeunDTO danggeun : danggeunList) {
	    	danggeun.setWriter_name(dangMemberDAO.selectMemberName(danggeun.getWriter_email()));
	        if(zzimIdSet.contains(danggeun.getId())) {
	            danggeun.setIsStoreByCurrentMember(true);
	        }
	    }

	    return danggeunList;
	}

	@Override
	public DanggeunDTO readDanggeun(Integer id) throws Exception {
		DanggeunDTO danggeunDTO = (DanggeunDTO) danggeunDAO.select(id);
		danggeunDAO.increaseViewCnt(id);
		return danggeunDTO;
	}
	
	
}
