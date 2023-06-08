package com.earth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.earth.domain.DanggeunInfoDTO;
import com.earth.domain.DanggeunSearchItem;

@Mapper
public interface DanggeunInfoMapper {
	
	//옵션을 이용해 불러온 상품 개수구할 때 사용
	int selectByOptionCnt(DanggeunSearchItem dsc) throws Exception;
	
	//옵션을 이용해 상품 리스트를 불러올 때 사용
	List<DanggeunInfoDTO> selectByOption(DanggeunSearchItem dsc) throws Exception;
	
	//게시물 하나 읽을 때 사용
	DanggeunInfoDTO select(Map map) throws Exception;
	
	//게시물 수정할 때 사용
	int update(DanggeunInfoDTO danggeunInfoDTO) throws Exception;
	
	//조회수 하나 올릴 때 사용
	int increaseViewCnt(Integer id) throws Exception;
	
	//게시물 찜하거나 찜 취소할 때 사용
	int updateZzimCnt(Map map) throws Exception;
	
	//게시물 추가할 때 사용
	int insert(DanggeunInfoDTO danggeunInfoDTO) throws Exception;
	
	//게시물 삭제할 때 사용
	int delete(Integer id) throws Exception;
	
}
