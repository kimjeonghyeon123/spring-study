package com.earth.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earth.domain.DogDto;
import com.earth.mapper.MemberMapper;
import com.earth.domain.MemberDto;

@Service
public class MemberServicelmpl implements MemberService {
	
	@Autowired
	MemberMapper membermapper;
	
	//회원가입
	@Override
	public void memberJoin(MemberDto member) throws Exception {
		membermapper.memberJoin(member);
		
	}
	
	// 이메일 중복 검사
	@Override
	public int emailCheck(String user_email) throws Exception {
		return membermapper.emailCheck(user_email);
	}
	
	/* 로그인 */
	@Override
	public MemberDto memberLogin(MemberDto member) throws Exception {
		return membermapper.memberLogin(member);
	}
	
	// 닉네임 중복검사
	@Override
	public int nickNameCheck(String user_nickname) throws Exception {
		return membermapper.nickNameCheck(user_nickname);
	}
	
	//강아지 정보 기입
	@Override
	public void dogInsert(MemberDto member) throws Exception {
		membermapper.dogInsert(member);
		
	}
	
	//회원정보가져오기
	@Override
	public MemberDto memberSelect(MemberDto member) throws Exception {

		return membermapper.memberSelect(member);
	}
	
	//도그정보가져오기
	@Override
	public DogDto dogSelect(DogDto dog) throws Exception {
		return membermapper.dogSelect(dog);
	}
	
	//신규회원확인하기
	@Override
	public MemberDto memberCheck(MemberDto member) throws Exception {
		
		return membermapper.memberCheck(member);
	}
	
	//이메일 찾기
	@Override
	public MemberDto findEmail(MemberDto member) throws Exception {

		return membermapper.findEmail(member);
	}
	
	//비밀번호 찾기
	@Override
	public MemberDto findPwd(MemberDto member) throws Exception {

		return membermapper.findPwd(member);
	}
	
	//비밀번호 변경
	@Override
	public void pwUpdate(MemberDto member) throws Exception {

		membermapper.pwUpdate(member);

	}
}
