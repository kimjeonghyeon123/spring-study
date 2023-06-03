package com.earth.danggeun.dao;

import java.util.List;

import com.earth.danggeun.domain.DanggeunDTO;
import com.earth.danggeun.domain.DanggeunSearchItem;

public interface DanggeunDao {

	//옵션을 이용해 불러온 상품 개수구할 때 사용
	int selectByOptionCnt(DanggeunSearchItem dsc) throws Exception;
	
	//옵션을 이용해 상품 리스트를 불러올 때 사용
	List<DanggeunDTO> selectByOption(DanggeunSearchItem dsc) throws Exception;
	
	//게시물 하나 읽을 때 사용
	DanggeunDTO select(Integer id, String login_email) throws Exception;
	
	//게시물 수정할 때 사용
	int update(DanggeunDTO danggeunDTO) throws Exception;
	
	//조회수 하나 올릴 때 사용
	int increaseViewCnt(Integer id) throws Exception;
	
	//게시물 찜하거나 찜 취소할 때 사용
	int updateZzimCnt(Integer id, int cnt) throws Exception;
	
	//게시물 추가할 때 사용
	int insert(DanggeunDTO danggeunDTO) throws Exception;
	
	//게시물 삭제할 때 사용
	//찜 테이블 데이터도 삭제해야 됨
	int delete(Integer id) throws Exception;
	
}
