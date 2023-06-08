package com.earth.mapper;

import com.earth.domain.ImageDto;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ImageMapperTest {
    @Autowired
    private ImageMapper imageMapper;

@Ignore
    @Test
    public void selectT() {
        ImageDto dto = new ImageDto();
        dto.setUser_email("test@test");
        dto.setPt_category("dogProfile");
        dto.setPt_adres("test@test");
        imageMapper.delete(dto);
//        imageMapper.insert(dto);
//        List<ImageDto> select = imageMapper.select(dto);
//        System.out.println(select.get(0));

    }

}