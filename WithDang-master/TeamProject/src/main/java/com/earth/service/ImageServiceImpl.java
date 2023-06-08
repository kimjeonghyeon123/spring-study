package com.earth.service;

import com.earth.domain.ImageDto;
import com.earth.mapper.ImageMapper;
import com.earth.upload.S3UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    ImageMapper mapper;
    @Autowired
    S3UploadService s3UploadService;
    
    //개 전용
    //채팅이나 게시물 같은 경우는 postNumber가 필요해서 따로 만들어야 함
    @Override
    public ImageDto inputDogProf(String id, String address, String category) {
        ImageDto imageDto = new ImageDto(address, category, id);
        //먼저 기존 이미지 삭제함. 삭제 기준은 id, 카테고리.
        ImageDto imageDto1 = callDogProf(id, category);
        //db에 없으면 패스
        if (imageDto1 != null) {
            //기존에 있던 이미지 객체에서 주소 꺼냄
            String ptAdres = imageDto1.getPt_adres();
            System.out.println("delete: "+ ptAdres.substring(57,ptAdres.length()));
            //서버에서 삭제하고
            s3UploadService.deleteFile(ptAdres);
            //db에서도 삭제
            mapper.delete(imageDto);
        }
        //새로 업로드 한 이미지를 db에 넣기
        mapper.insert(imageDto);
        return imageDto;
    }

    @Override
    public ImageDto callDogProf(String id, String category) {
//        where user_email = #{user_email}
//        and pt_category = #{pt_category}
        ImageDto dto = new ImageDto(category,id);
        try {
            //복수 업로드를 기준으로 만들어서 list임
            List<ImageDto> select = mapper.select(dto);
            return select.get(0);

        } catch (IndexOutOfBoundsException e){
            e.getMessage();
            return null;
        }

    }

}
