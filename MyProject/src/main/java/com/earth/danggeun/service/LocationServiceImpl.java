package com.earth.danggeun.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.earth.danggeun.dao.DongDao;
import com.earth.danggeun.dao.SidoDao;
import com.earth.danggeun.dao.SigoonDao;
import com.earth.danggeun.domain.DongDTO;
import com.earth.danggeun.domain.SidoDTO;
import com.earth.danggeun.domain.SigoonDTO;

@Service
public class LocationServiceImpl implements LocationService {

	private SidoDao sidoDao;
	private SigoonDao sigoonDao;
	private DongDao dongDao;
	
	public LocationServiceImpl(SidoDao sidoDao, SigoonDao sigoonDao, DongDao dongDao) {
		this.sidoDao = sidoDao;
		this.sigoonDao = sigoonDao;
		this.dongDao = dongDao;
	}

	@Override
	public List<SidoDTO> readSido() throws Exception {
		return sidoDao.selectAll();
	}

	@Override
	public List<SigoonDTO> readSigoon(String sido_code) throws Exception {
		return sigoonDao.selectBySido(sido_code);
	}

	@Override
	public List<DongDTO> readDong(String sigoon_code) throws Exception {
		return dongDao.selectBySigoon(sigoon_code);
	}

	@Override
	public SidoDTO readSidoOne(String sido_code) throws Exception {
		return sidoDao.select(sido_code);
	}

	@Override
	public SigoonDTO readSigoonOne(String sigoon_code) throws Exception {
		return sigoonDao.select(sigoon_code);
	}

	@Override
	public DongDTO readDongOne(String dong_code) throws Exception {
		return dongDao.select(dong_code);
	}
	
}
