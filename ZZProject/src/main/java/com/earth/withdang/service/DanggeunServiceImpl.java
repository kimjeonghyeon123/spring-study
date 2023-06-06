package com.earth.withdang.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earth.withdang.dao.AddressDongDAO;
import com.earth.withdang.dao.AddressSidoDAO;
import com.earth.withdang.dao.AddressSigoonDAO;
import com.earth.withdang.dao.DanggeunInfoDAO;
import com.earth.withdang.dao.DanggeunMemberDAO;
import com.earth.withdang.dao.DanggeunPhotoDAO;
import com.earth.withdang.dao.DanggeunTypeDAO;
import com.earth.withdang.dao.DanggeunZzimDAO;
import com.earth.withdang.domain.DanggeunInfoDTO;
import com.earth.withdang.domain.DanggeunSearchItem;
import com.earth.withdang.domain.DanggeunTypeDTO;
import com.earth.withdang.domain.DanggeunZzimDTO;

@Service
public class DanggeunServiceImpl implements DanggeunService {

	private DanggeunInfoDAO danggeunInfoDAO;
	private DanggeunTypeDAO danggeunTypeDAO;
	private DanggeunPhotoDAO danggeunPhotoDAO;
	private DanggeunZzimDAO danggeunZzimDAO;
	private AddressSidoDAO addressSidoDAO;
	private AddressSigoonDAO addressSigoonDAO;
	private AddressDongDAO addressDongDAO;
	
	public DanggeunServiceImpl(DanggeunInfoDAO danggeunInfoDAO, DanggeunTypeDAO danggeunTypeDAO,
			DanggeunPhotoDAO danggeunPhotoDAO, DanggeunZzimDAO danggeunZzimDAO, AddressSidoDAO addressSidoDAO,
			AddressSigoonDAO addressSigoonDAO, AddressDongDAO addressDongDAO) {
		this.danggeunInfoDAO = danggeunInfoDAO;
		this.danggeunTypeDAO = danggeunTypeDAO;
		this.danggeunPhotoDAO = danggeunPhotoDAO;
		this.danggeunZzimDAO = danggeunZzimDAO;
		this.addressSidoDAO = addressSidoDAO;
		this.addressSigoonDAO = addressSigoonDAO;
		this.addressDongDAO = addressDongDAO;
	}

	@Override
	public int countDanggeunListByOption(DanggeunSearchItem dsc) throws Exception {
		return danggeunInfoDAO.selectByOptionCnt(dsc);
	}

	@Override
	public List<DanggeunInfoDTO> readDanggeunListByOption(DanggeunSearchItem dsc, String login_nickname)
			throws Exception {
		dsc.setLogin_nickname(login_nickname);
		return danggeunInfoDAO.selectByOption(dsc);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public DanggeunInfoDTO readDanggeun(Integer id, String login_nickname) throws Exception {
		DanggeunInfoDTO danggeunInfoDTO = danggeunInfoDAO.select(id, login_nickname);
		danggeunInfoDAO.increaseViewCnt(id);
		return danggeunInfoDTO;
	}

	//사진 추가하는거 넣어야 될듯?
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void modifyDangguen(DanggeunInfoDTO danggeunInfoDTO) throws Exception {
		danggeunInfoDTO.setType_name(danggeunTypeDAO.select(danggeunInfoDTO.getType_id()).getName());
		danggeunInfoDTO.setSido_name(addressSidoDAO.select(danggeunInfoDTO.getSido_code()).getName());
		danggeunInfoDTO.setSigoon_name(addressSigoonDAO.select(danggeunInfoDTO.getSigoon_code()).getName());
		danggeunInfoDTO.setDong_name(addressDongDAO.select(danggeunInfoDTO.getDong_name()).getName());
		danggeunInfoDAO.update(danggeunInfoDTO);
	}

	//사진 추가하는거 넣어야 될듯?
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void registerDanggeun(DanggeunInfoDTO danggeunInfoDTO) throws Exception {
		danggeunInfoDTO.setType_name(danggeunTypeDAO.select(danggeunInfoDTO.getType_id()).getName());
		danggeunInfoDTO.setSido_name(addressSidoDAO.select(danggeunInfoDTO.getSido_code()).getName());
		danggeunInfoDTO.setSigoon_name(addressSigoonDAO.select(danggeunInfoDTO.getSigoon_code()).getName());
		danggeunInfoDTO.setDong_name(addressDongDAO.select(danggeunInfoDTO.getDong_name()).getName());
		danggeunInfoDAO.insert(danggeunInfoDTO);
	}

	
	@Override
	public void removeDanggeun(Integer id) throws Exception {
		danggeunInfoDAO.delete(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void pushZzim(String user_nickname, Integer danggeun_id) throws Exception {
		danggeunZzimDAO.insert(new DanggeunZzimDTO(user_nickname, danggeun_id));
		danggeunInfoDAO.updateZzimCnt(danggeun_id, 1);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void cancelZzim(String user_nickname, Integer danggeun_id) throws Exception {
		danggeunZzimDAO.delete(new DanggeunZzimDTO(user_nickname, danggeun_id));
		danggeunInfoDAO.updateZzimCnt(danggeun_id, -1);
	}

	@Override
	public List<DanggeunTypeDTO> getTypeList() throws Exception {
		return danggeunTypeDAO.selectAll();
	}

}
