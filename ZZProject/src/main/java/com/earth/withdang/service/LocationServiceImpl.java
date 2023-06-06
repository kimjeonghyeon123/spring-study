package com.earth.withdang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.earth.withdang.dao.AddressDongDAO;
import com.earth.withdang.dao.AddressSidoDAO;
import com.earth.withdang.dao.AddressSigoonDAO;
import com.earth.withdang.domain.AddressDongDTO;
import com.earth.withdang.domain.AddressSidoDTO;
import com.earth.withdang.domain.AddressSigoonDTO;

@Service
public class LocationServiceImpl implements LocationService {

	private AddressSidoDAO addressSidoDAO;
	private AddressSigoonDAO addressSigoonDAO;
	private AddressDongDAO addressDongDAO;
	
	public LocationServiceImpl(AddressSidoDAO addressSidoDAO, AddressSigoonDAO addressSigoonDAO,
			AddressDongDAO addressDongDAO) {
		this.addressSidoDAO = addressSidoDAO;
		this.addressSigoonDAO = addressSigoonDAO;
		this.addressDongDAO = addressDongDAO;
	}
	
	@Override
	public List<AddressSidoDTO> readSido() throws Exception {
		return addressSidoDAO.selectAll();
	}
	@Override
	public List<AddressSigoonDTO> readSigoon(String sido_code) throws Exception {
		return addressSigoonDAO.selectBySido(sido_code);
	}
	@Override
	public List<AddressDongDTO> readDong(String sigoon_code) throws Exception {
		return addressDongDAO.selectBySigoon(sigoon_code);
	}
	@Override
	public AddressSidoDTO readSidoOne(String code) throws Exception {
		return addressSidoDAO.select(code);
	}
	@Override
	public AddressSigoonDTO readSigoonOne(String code) throws Exception {
		return addressSigoonDAO.select(code);
	}
	@Override
	public AddressDongDTO readDongOne(String code) throws Exception {
		return addressDongDAO.select(code);
	}
	

	
}
