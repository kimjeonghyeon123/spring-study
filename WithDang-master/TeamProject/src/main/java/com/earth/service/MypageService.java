package com.earth.service;

import com.earth.domain.DogDto;
import com.earth.domain.MemberDto;

public interface MypageService {

	/* 회원정보수정 */
    public void memberUpdate(MemberDto member) throws Exception;
    
    //강아지 정보 수정
    public void dogUpdate(DogDto dog) throws Exception;

}
