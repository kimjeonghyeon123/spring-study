package com.earth.service;

import java.util.List;

import com.earth.domain.DanggeunInfoDTO;
import com.earth.domain.DanggeunSearchItem;
import com.earth.domain.DanggeunTypeDTO;

public interface DanggeunService {
	
	//옵션을 이용해 불러온 상품 개수구할 때 사용
	int countDanggeunListByOption(DanggeunSearchItem dsc) throws Exception;
	
	//옵션을 이용해 상품 리스트를 불러올 때 사용
	//로그인한 멤버의 찜 여부까지 파악해서 상품목록 반환함
	List<DanggeunInfoDTO> readDanggeunListByOption(DanggeunSearchItem dsc, String login_nickname) throws Exception;
	
	//게시물 하나 읽을 때 사용
	//게시물 조회수 올라감
	//찜 여부 파악
	DanggeunInfoDTO readDanggeun(Integer id, String login_nickname) throws Exception;
	
	//게시물 수정할 때 사용
	void modifyDangguen(DanggeunInfoDTO danggeunInfoDTO, List<String> list) throws Exception;
	
	//게시물 추가할 때 사용
	void registerDanggeun(DanggeunInfoDTO danggeunInfoDTO, List<String> list) throws Exception;
	
	//게시물 삭제할 때 사용
	void removeDanggeun(Integer id) throws Exception;
	
	//당근 찜하기 눌렀을 때 사용
	//해당 상품의 찜 숫자가 올라가야 됨
	void pushZzim(String user_nickname, Integer danggeun_id) throws Exception;
	
	//멤버가 상품 찜을 취소했을 때 사용
	//해당 상품의 찜 숫자가 내려가야 됨
	void cancelZzim(String user_nickname, Integer danggeun_id) throws Exception;
	
	//댕근 타입 목록 가져오기
	List<DanggeunTypeDTO> getTypeList() throws Exception;
	
}
