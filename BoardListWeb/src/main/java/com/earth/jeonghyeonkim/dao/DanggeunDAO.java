package com.earth.jeonghyeonkim.dao;

import java.util.List;

import com.earth.jeonghyeonkim.domain.DanggeunDTO;
import com.earth.jeonghyeonkim.domain.SearchItem;

public interface DanggeunDAO {
	
	//옵션을 이용해 불러온 상품 개수
	int selectByOptionCnt(SearchItem sc) throws Exception;

	//옵션을 이용해 상품 리스트를 불러올 때 사용 
	List<DanggeunDTO> selectByOption(SearchItem sc) throws Exception;
	
	//게시물 하나를 읽을 때 사용
	DanggeunDTO select(Integer id) throws Exception;
	
	//게시물 읽으면 조회수가 올라가게 할 때 사용
	int increaseViewCnt(Integer id) throws Exception;
	
	//게시물을 찜하거나 취소할 때 사용
	int updateZzimCnt(Integer id, int cnt) throws Exception;
	
	//상품 추가할 때 사용
	int insert(DanggeunDTO danggeunDTO) throws Exception;

	//상품 삭제할 때 사용
	//찜 테이블 가서 데이터도 삭제해야 됨
	int delete(Integer id, String writer_email) throws Exception;
	
}
