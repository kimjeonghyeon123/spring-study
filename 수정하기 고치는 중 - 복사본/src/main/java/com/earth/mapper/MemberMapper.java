package com.earth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.earth.domain.DogDto;
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
	
	//로그인
    public MemberDto memberLogin(MemberDto member);
    
    //회원정보가져오기
    public MemberDto memberSelect(MemberDto member);

    //도그정보가져오기
    public DogDto dogSelect(DogDto dog);
    
    //신규회원확인하기
    public MemberDto memberCheck(MemberDto member);
   
    //이메일 찾기
    public MemberDto findEmail(MemberDto member);

    //비밀번호 찾기
    public MemberDto findPwd(MemberDto member);

    //비밀번호 변경
    public void pwUpdate(MemberDto member);
    
}
