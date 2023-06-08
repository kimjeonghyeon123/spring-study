package com.earth.service;

import com.earth.domain.DogDto;
import com.earth.domain.MemberDto;
import com.earth.domain.MemberInfoDto;

public interface MypageService {

	/* 회원정보수정 */
    public void memberUpdate(MemberDto member) throws Exception;
    
    //강아지 정보 수정
    public void dogUpdate(DogDto dog) throws Exception;
    
    //회원정보가져오기
    public MemberDto memberSelect(MemberDto member) throws Exception;
    
    //도그정보가져오기
    public DogDto dogSelect(DogDto dog) throws Exception;
}
