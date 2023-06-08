package com.earth.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

	@Select("SELECT NOW()")
	public String getTime1();
	
	public String getTime2();
}
