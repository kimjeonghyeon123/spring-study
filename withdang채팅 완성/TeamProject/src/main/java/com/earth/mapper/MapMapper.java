package com.earth.mapper;

import com.earth.domain.MapVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MapMapper {
    public void insertPin(MapVo mapVo);


    public List<MapVo> selectPin(String pinType);

    public void truncate();

}
