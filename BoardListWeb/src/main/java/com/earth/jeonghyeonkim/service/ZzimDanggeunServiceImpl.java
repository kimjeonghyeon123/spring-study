package com.earth.jeonghyeonkim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earth.jeonghyeonkim.dao.DanggeunDAO;
import com.earth.jeonghyeonkim.dao.ZzimDanggeunDAO;
import com.earth.jeonghyeonkim.domain.ZzimDanggeunDTO;

@Service
public class ZzimDanggeunServiceImpl implements ZzimDanggeunService {

	@Autowired
	ZzimDanggeunDAO zzimDanggeunDAO;
	@Autowired
	DanggeunDAO danggeunDAO;

	@Override
	public boolean checkZzim(ZzimDanggeunDTO zzimDanggeunDTO) throws Exception {
		return zzimDanggeunDAO.selectcount(zzimDanggeunDTO) == 1;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int pushZzim(ZzimDanggeunDTO zzimDanggeunDTO) throws Exception {
		int cnt = zzimDanggeunDAO.insert(zzimDanggeunDTO);
		danggeunDAO.updateZzimCnt(zzimDanggeunDTO.getDanggeun_id(), 1);
		return cnt;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int cancelZzim(ZzimDanggeunDTO zzimDanggeunDTO) throws Exception {
		int cnt = zzimDanggeunDAO.delete(zzimDanggeunDTO);
		danggeunDAO.updateZzimCnt(zzimDanggeunDTO.getDanggeun_id(), -1);
		return cnt;
	}
	
	
	
}
