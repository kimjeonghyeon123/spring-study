package com.earth.mapper;

import org.apache.ibatis.annotations.Param;

import com.earth.domain.DogDto;
import com.earth.domain.MemberInfoDto;
import com.earth.domain.MemberDto;


public interface MemberMapper {
	
	//회원가입
	public void memberJoin(MemberDto member);
	
	//강아지 정보 기입
	public void dogInsert(MemberDto member);
	
	// 이메일 중복 검사
	public int emailCheck(String user_email);
	
	// 닉네임 중복 검사
	public int nickNameCheck(String user_nickname);
	
	/* 로그인 */
    public MemberDto memberLogin(MemberDto member);
    
    //도그정보가져오기
    public DogDto dogSelect(DogDto dog);
    
    //신규회원확인하기
    public MemberDto memberCheck(MemberDto member);
   
}
