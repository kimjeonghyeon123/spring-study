package com.earth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earth.domain.DogDto;
import com.earth.domain.MemberDto;
import com.earth.domain.MemberInfoDto;
import com.earth.mapper.MypageMapper;

@Service
public class MypageServiceImpl implements MypageService {

	@Autowired
	MypageMapper myMapper;
	
	@Override
	public void memberUpdate(MemberDto member) throws Exception {
		myMapper.memberUpdate(member);
		
	}

	@Override
	public void dogUpdate(DogDto dog) throws Exception {
		myMapper.dogUpdate(dog);
		
	}

	@Override
	public MemberDto memberSelect(MemberDto member) throws Exception {
		
		return myMapper.memberSelect(member);
	}

	@Override
	public DogDto dogSelect(DogDto dog) throws Exception {
		
		return myMapper.dogSelect(dog);
	}

}
