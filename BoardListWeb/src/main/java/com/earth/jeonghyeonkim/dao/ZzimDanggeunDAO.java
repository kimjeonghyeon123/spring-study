package com.earth.jeonghyeonkim.dao;

import java.util.List;

import com.earth.jeonghyeonkim.domain.ZzimDanggeunDTO;

public interface ZzimDanggeunDAO {
	
	//찜 데이터 있는지 확인하기 있으면 1, 없으면 0
	int selectcount(ZzimDanggeunDTO zzimDanggeunDTO) throws Exception;
	
	//가져오기
	//email로 멤버가 찜한 상품id 리스트 가져오기
	List<Integer> selectByMemberEmail(String member_email) throws Exception;
	
	//상품id로 상품을 찜한 멤버 리스트 가져오기
	List<String> selectByMemberEmail(Integer danggeun_id) throws Exception;
	
	//------------------------------------------------------
	//삭제
	//상품이 팔리거나 글을 삭제했을 때 사용
	int deleteAllByDanggeunId(Integer danggeun_id) throws Exception;
	
	//멤버가 탈퇴하거나 찜한 목록을 다 취소할 때 사용
	//해당된 상품들의 찜 수를 다 내려야 됨
	int deleteAllByMemberEmail(String member_email) throws Exception;
	
	//멤버가 상품 찜을 취소했을 때 사용
	//해당 상품의 찜 숫자가 내려가야 됨
	int delete(ZzimDanggeunDTO zzimDanggeunDTO) throws Exception;
	
	//------------------------------------------------------
	//삽입
	//멤버가 상품 찜하기 눌렀을 때 사용
	//해당 상품의 찜 숫자가 올라가야 됨
	int insert(ZzimDanggeunDTO zzimDanggeunDTO) throws Exception;
}
