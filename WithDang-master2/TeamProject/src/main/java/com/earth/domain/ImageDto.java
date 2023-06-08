package com.earth.domain;

import lombok.Data;
//럼복 사용
//게터세터투스트링 등등을 어노테이션 하나로 쓰기 위함
@Data
public class ImageDto {
    private String pt_adres;
    private String pt_category;
    //email
    private String user_email;

    //게시판의 경우
    //커뮤와 마켓은 카테고리 차이로 구분?
    //채팅방도?
    private int postNum;

    private int pt_idx;
    
    private int photo_num; // dangcomu 게시물 하나의 사진 순서

    public ImageDto() {
    }

    public ImageDto(String pt_category, String user_email) {
        this.pt_category = pt_category;
        this.user_email = user_email;
    }

    public ImageDto(String pt_adres, String pt_category, String user_email) {
        this.pt_adres = pt_adres;
        this.pt_category = pt_category;
        this.user_email = user_email;
    }

    public ImageDto(String pt_adres, String pt_category, String user_email, int postNum) {
        this.pt_adres = pt_adres;
        this.pt_category = pt_category;
        this.user_email = user_email;
        this.postNum = postNum;
    }

}
