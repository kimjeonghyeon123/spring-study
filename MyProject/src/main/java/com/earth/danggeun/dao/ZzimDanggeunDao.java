package com.earth.danggeun.dao;

import java.util.List;

import com.earth.danggeun.domain.ZzimDanggeunDTO;

public interface ZzimDanggeunDao {

	//email로 멤버가 찜한 상품id 리스트 가져오기 
	List<ZzimDanggeunDTO> selectByMemberEmail(String email) throws Exception;
	
	//상품id로 상품을 찜한 리스트 가져오기
	List<ZzimDanggeunDTO> selectByDanggeunId(Integer id) throws Exception; 
	
	//멤버가 상품 찜하기 눌렀을 때 사용
	//해당 상품의 찜 숫자가 올라가야 됨
	int insert(ZzimDanggeunDTO zzimDanggeunDto) throws Exception;
	
	//멤버가 탈퇴하거나 찜한 목록을 다 취소할 때 사용
	//해당된 상품들의 찜 수를 다 내려야 됨
	int deleteAllByMemberEmail(String email) throws Exception;
	
	//상품을 삭제했을 때 사용
	int deleteAllByDanggeunId(Integer id) throws Exception;
	
	//멤버가 상품 찜을 취소했을 때 사용
	//해당 상품의 찜 숫자가 내려가야 됨
	int delete(ZzimDanggeunDTO zzimDanggeunDto) throws Exception;
	
}
