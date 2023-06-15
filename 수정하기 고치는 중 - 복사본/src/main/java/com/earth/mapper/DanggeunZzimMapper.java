package com.earth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.earth.domain.DanggeunZzimDTO;

@Mapper
public interface DanggeunZzimMapper {
	
	//email로 멤버가 찜한 상품id 리스트 가져오기 
	List<DanggeunZzimDTO> selectByUserNickname(String user_nickname) throws Exception;
	
	//상품id로 상품을 찜한 리스트 가져오기
	List<DanggeunZzimDTO> selectByDanggeunId(Integer danggeun_id) throws Exception; 
	
	//멤버가 상품 찜하기 눌렀을 때 사용
	//해당 상품의 찜 숫자가 올라가야 됨
	int insert(DanggeunZzimDTO DanggeunZzimDTO) throws Exception;
	
	//멤버가 탈퇴하거나 찜한 목록을 다 취소할 때 사용
	//해당된 상품들의 찜 수를 다 내려야 됨
	int deleteAllByUserNickname(String user_nickname) throws Exception;
	
	//멤버가 상품 찜을 취소했을 때 사용
	//해당 상품의 찜 숫자가 내려가야 됨
	int delete(DanggeunZzimDTO DanggeunZzimDTO) throws Exception;
}
