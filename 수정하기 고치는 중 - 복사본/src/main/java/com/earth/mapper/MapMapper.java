package com.earth.mapper;

import com.earth.domain.MapVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface MapMapper {
    public void insertPin(MapVo mapVo);


//    public List<MapVo> selectPin(String pinType);

    public List<MapVo> selectPinNear(MapVo mapVo);

    public void truncate();

}
