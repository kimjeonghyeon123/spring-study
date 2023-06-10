package com.earth.service;

import java.util.List;

import com.earth.domain.ImageDto;
import com.earth.mapper.ImageMapper;

public interface ImageService {
    //개 프로필
    //                values (#{pt_adres},#{pt_category},#{user_email})
    public ImageDto inputDogProf(String id, String address, String category);
    public ImageDto callDogProf(String id, String category);

    // dangcomu post 이미지
    public ImageDto inputComuPhoto(String user_email, int post_id, String address, String category);
    public List<ImageDto> callComuPhoto(String user_email, int post_id, String category);
    public void deleteComuPhoto(String user_email, int post_id, String category);
}
