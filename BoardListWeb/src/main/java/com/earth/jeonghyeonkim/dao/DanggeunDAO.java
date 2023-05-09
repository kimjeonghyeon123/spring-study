package com.earth.jeonghyeonkim.dao;

import java.util.List;
import java.util.Map;

import com.earth.jeonghyeonkim.domain.DanggeunDTO;

public interface DanggeunDAO {
	
	DanggeunDTO select(Integer id) throws Exception;
	List<DanggeunDTO> selectAll() throws Exception;
	List<DanggeunDTO> selectByType(Integer type_id) throws Exception;
	int insert(DanggeunDTO danggeunDTO) throws Exception;
	int delete(Integer id, String writer) throws Exception;
	int addViewCnt(Integer id) throws Exception;
	int addAddCnt(Integer id) throws Exception;
	int insertStore(Integer id, String writer) throws Exception;
	
}
