package com.earth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.earth.mapper.AddressDongMapper;
import com.earth.mapper.AddressSidoMapper;
import com.earth.mapper.AddressSigoonMapper;
import com.earth.domain.AddressDongDTO;
import com.earth.domain.AddressSidoDTO;
import com.earth.domain.AddressSigoonDTO;

@Service
public class LocationServiceImpl implements LocationService {

	private AddressSidoMapper addressSidoMapper;
	private AddressSigoonMapper addressSigoonMapper;
	private AddressDongMapper addressDongMapper;
	
	public LocationServiceImpl(AddressSidoMapper addressSidoMapper, AddressSigoonMapper addressSigoonMapper,
			AddressDongMapper addressDongMapper) {
		this.addressSidoMapper = addressSidoMapper;
		this.addressSigoonMapper = addressSigoonMapper;
		this.addressDongMapper = addressDongMapper;
	}
	
	@Override
	public List<AddressSidoDTO> readSido() throws Exception {
		return addressSidoMapper.selectAll();
	}
	@Override
	public List<AddressSigoonDTO> readSigoon(String sido_code) throws Exception {
		return addressSigoonMapper.selectBySido(sido_code);
	}
	@Override
	public List<AddressDongDTO> readDong(String sigoon_code) throws Exception {
		return addressDongMapper.selectBySigoon(sigoon_code);
	}
	@Override
	public AddressSidoDTO readSidoOne(String code) throws Exception {
		return addressSidoMapper.select(code);
	}
	@Override
	public AddressSigoonDTO readSigoonOne(String code) throws Exception {
		return addressSigoonMapper.select(code);
	}
	@Override
	public AddressDongDTO readDongOne(String code) throws Exception {
		return addressDongMapper.select(code);
	}
	

	
}
