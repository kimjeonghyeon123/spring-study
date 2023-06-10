package com.earth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earth.domain.DogDto;
import com.earth.domain.MemberDto;
import com.earth.mapper.MypageMapper;

@Service
public class MypageServiceImpl implements MypageService {

	@Autowired
	MypageMapper myMapper;
	
	/* 회원정보수정 */
	@Override
	public void memberUpdate(MemberDto member) throws Exception {
		myMapper.memberUpdate(member);
		
	}
	
	//강아지 정보 수정
	@Override
	public void dogUpdate(DogDto dog) throws Exception {
		myMapper.dogUpdate(dog);
		
	}

}
