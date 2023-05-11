package com.earth.jeonghyeonkim.service;

import com.earth.jeonghyeonkim.domain.ZzimDanggeunDTO;

public interface ZzimDanggeunService {
	
	//찜 데이터 있는지 확인하기 있으면 1, 없으면 0
	boolean checkZzim(ZzimDanggeunDTO zzimDanggeunDTO) throws Exception;
	

	//찜 눌렀을 때 사용
	//해당 상품의 찜 숫자가 올라가야 됨
	int pushZzim(ZzimDanggeunDTO zzimDanggeunDTO) throws Exception;
	
	//멤버가 상품 찜을 취소했을 때 사용
	//해당 상품의 찜 숫자가 내려가야 됨
	int cancelZzim(ZzimDanggeunDTO zzimDanggeunDTO) throws Exception;
	
}
