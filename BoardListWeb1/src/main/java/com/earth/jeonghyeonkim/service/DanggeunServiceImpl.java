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
	public List<DanggeunDTO> readDanggeunListByOption(Integer type_id) throws Exception {
		if(type_id == 0) {
			return danggeunDAO.selectAll();
		}
		
		//추가해놓은 변수들을 설정 후에 return
		List<DanggeunDTO> list = danggeunDAO.selectByOption(type_id);
		for(DanggeunDTO dg : list) {
			//멤버dao에서 멤버 이름 가져옴 dg.setWriter_email();
			//찜dao에서 찜한 테이블 번호 가져옴 dg.setIstStoreByCurrentMember();
			//찜은 for문으로 돌려서 적용
		}
		
		return list;
	}

	@Override
	public DanggeunDTO readDanggeun(Integer id) throws Exception {
		DanggeunDTO danggeunDTO = (DanggeunDTO) danggeunDAO.select(id);
		danggeunDAO.increaseViewCnt(id);
		return danggeunDTO;
	}
	
	
}
