package com.earth.jeonghyeonkim.service;

import java.util.List;

import com.earth.jeonghyeonkim.domain.DanggeunDTO;
import com.earth.jeonghyeonkim.domain.SearchItem;
import com.earth.jeonghyeonkim.domain.ZzimDanggeunDTO;

public interface DanggeunService {
	
	//옵션을 이용해 불러온 상품 개수
	int countDanggeunListByOption(SearchItem sc) throws Exception;
	
	//옵션을 이용해 상품 리스트를 불러올 때 사용 
	//로그인한 멤버의 찜 여부까지 파악해서 상품목록 반환함
	List<DanggeunDTO> readDanggeunListByOption(SearchItem sc, String login_email) throws Exception;
	
	//게시물 하나를 읽을 때 사용
	//게시물의 조회수가 올라감
	DanggeunDTO readDanggeun(Integer id, String login_email) throws Exception;
	
	//게시물 정보 가져오기
	DanggeunDTO loadDanggeun(Integer id) throws Exception;
	
	//상픔 등록하기
	int registerDanggeun(DanggeunDTO danggeunDTO) throws Exception;
	
	//상품 삭제할 때 사용
	//찜 테이블 가서 데이터도 삭제해야 됨
	int remove(Integer id, String writer_email) throws Exception;
	
}
