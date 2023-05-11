package com.earth.jeonghyeonkim.dao;

import java.util.List;

import com.earth.jeonghyeonkim.domain.DanggeunDTO;

public interface DanggeunDAO {
	
	List<DanggeunDTO> selectAll() throws Exception;
	
	//검색 옵션을 이용해서 상품 목록 조회할 때 사용 
	List<DanggeunDTO> selectByOption(Integer type_id) throws Exception;
	
	//게시물 하나를 읽을 때 사용
	List<DanggeunDTO> select(Integer id) throws Exception;
	
	//게시물 읽으면 조회수가 올라가게 할 때 사용
	int increaseViewCnt(Integer id) throws Exception;
	
	//게시물을 찜하거나 취소할 때 사용
	int updateZzimCnt(Integer id, int cnt) throws Exception;
	
}
