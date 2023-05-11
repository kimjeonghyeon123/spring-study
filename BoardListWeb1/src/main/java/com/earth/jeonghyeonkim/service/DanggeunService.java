package com.earth.jeonghyeonkim.service;

import java.util.List;

import com.earth.jeonghyeonkim.domain.DanggeunDTO;

public interface DanggeunService {
	
	//검색 옵션을 이용해서 상품 목록 조회할 때 사용 
	List<DanggeunDTO> readDanggeunListByOption(Integer type_id) throws Exception;
	
	//게시물 하나를 읽을 때 사용
	DanggeunDTO readDanggeun(Integer id) throws Exception;
	
}
