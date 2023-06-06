package com.earth.withdang.dao;

import java.util.List;

import com.earth.withdang.domain.DanggeunInfoDTO;
import com.earth.withdang.domain.DanggeunSearchItem;

public interface DanggeunInfoDAO {
	
	//옵션을 이용해 불러온 상품 개수구할 때 사용
	int selectByOptionCnt(DanggeunSearchItem dsc) throws Exception;
	
	//옵션을 이용해 상품 리스트를 불러올 때 사용
	List<DanggeunInfoDTO> selectByOption(DanggeunSearchItem dsc) throws Exception;
	
	//게시물 하나 읽을 때 사용
	DanggeunInfoDTO select(Integer id, String login_nickname) throws Exception;
	
	//게시물 수정할 때 사용
	int update(DanggeunInfoDTO danggeunInfoDTO) throws Exception;
	
	//조회수 하나 올릴 때 사용
	int increaseViewCnt(Integer id) throws Exception;
	
	//게시물 찜하거나 찜 취소할 때 사용
	int updateZzimCnt(Integer id, int cnt) throws Exception;
	
	//게시물 추가할 때 사용
	int insert(DanggeunInfoDTO danggeunInfoDTO) throws Exception;
	
	//게시물 삭제할 때 사용
	int delete(Integer id) throws Exception;
	
}
