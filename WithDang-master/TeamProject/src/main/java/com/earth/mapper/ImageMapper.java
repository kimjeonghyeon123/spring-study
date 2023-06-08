package com.earth.mapper;

import com.earth.domain.ImageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ImageMapper {
    //                values (#{pt_adres},#{pt_category},#{user_email})
    //                values (#{pt_adres},#{pt_category},#{user_email},#{pnum})
    public void insert(ImageDto dto);

    public List<ImageDto> select(ImageDto dto);

    public void delete(ImageDto dto);
}
