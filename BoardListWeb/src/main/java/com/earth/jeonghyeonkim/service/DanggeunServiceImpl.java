package com.earth.jeonghyeonkim.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earth.jeonghyeonkim.dao.DangMemberDAO;
import com.earth.jeonghyeonkim.dao.DanggeunDAO;
import com.earth.jeonghyeonkim.dao.ZzimDanggeunDAO;
import com.earth.jeonghyeonkim.domain.DanggeunDTO;
import com.earth.jeonghyeonkim.domain.SearchItem;
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
	public int countDanggeunListByOption(SearchItem sc) throws Exception {
		return danggeunDAO.selectByOptionCnt(sc);
	}
	
	@Override
	public List<DanggeunDTO> readDanggeunListByOption(SearchItem sc, String login_email) throws Exception {

	    Set<Integer> zzimIdSet = new HashSet<>(zzimDanggeunDAO.selectByMemberEmail(login_email));
	    List<DanggeunDTO> danggeunList = null;
	    
	    danggeunList = danggeunDAO.selectByOption(sc);
	    
	    for(DanggeunDTO danggeun : danggeunList) {
	        if(zzimIdSet.contains(danggeun.getId())) {
	            danggeun.setIsStoreByCurrentMember(true);
	        }
	    }

	    return danggeunList;
	}

	@Override
	public DanggeunDTO readDanggeun(Integer id, String login_email) throws Exception {
		DanggeunDTO danggeunDTO = (DanggeunDTO) danggeunDAO.select(id);
		danggeunDTO.setIsStoreByCurrentMember(zzimDanggeunDAO.selectcount(new ZzimDanggeunDTO(login_email, id)) == 1);
		danggeunDAO.increaseViewCnt(id);
		return danggeunDTO;
	}

	@Override
	public DanggeunDTO loadDanggeun(Integer id) throws Exception {
		return danggeunDAO.select(id);
	}

	@Override
	public int registerDanggeun(DanggeunDTO danggeunDTO) throws Exception {
		return danggeunDAO.insert(danggeunDTO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int remove(Integer id, String writer_email) throws Exception {
		int rowCnt = danggeunDAO.delete(id, writer_email);
		zzimDanggeunDAO.deleteAllByDanggeunId(id);
		return rowCnt;
	}
	
}