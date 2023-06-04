package com.earth.danggeun.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earth.danggeun.dao.DangMemberDao;
import com.earth.danggeun.dao.DanggeunDao;
import com.earth.danggeun.dao.DanggeunTypeDao;
import com.earth.danggeun.dao.DongDao;
import com.earth.danggeun.dao.SidoDao;
import com.earth.danggeun.dao.SigoonDao;
import com.earth.danggeun.dao.ZzimDanggeunDao;
import com.earth.danggeun.domain.DanggeunDTO;
import com.earth.danggeun.domain.DanggeunSearchItem;
import com.earth.danggeun.domain.DanggeunTypeDTO;
import com.earth.danggeun.domain.ZzimDanggeunDTO;

@Service
public class DanggeunServiceImpl implements DanggeunService {

	private DanggeunDao danggeunDao;
	private DanggeunTypeDao danggeunTypeDao;
	private ZzimDanggeunDao zzimDanggeunDao;
	private SidoDao sidoDao;
	private SigoonDao sigoonDao;
	private DongDao dongDao;
	private DangMemberDao dangMemberDao;


	public DanggeunServiceImpl(DanggeunDao danggeunDao, DanggeunTypeDao danggeunTypeDao,
			ZzimDanggeunDao zzimDanggeunDao, SidoDao sidoDao, SigoonDao sigoonDao, DongDao dongDao, DangMemberDao dangMemberDao) {
		this.danggeunDao = danggeunDao;
		this.danggeunTypeDao = danggeunTypeDao;
		this.zzimDanggeunDao = zzimDanggeunDao;
		this.sidoDao = sidoDao;
		this.sigoonDao = sigoonDao;
		this.dongDao = dongDao;
		this.dangMemberDao = dangMemberDao;
	}

	@Override
	public int countDanggeunListByOption(DanggeunSearchItem dsc) throws Exception {
		return danggeunDao.selectByOptionCnt(dsc);
	}

	@Override
	public List<DanggeunDTO> readDanggeunListByOption(DanggeunSearchItem dsc, String login_email) throws Exception {
		dsc.setLogin_email(login_email);
		return danggeunDao.selectByOption(dsc);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public DanggeunDTO readDanggeun(Integer id, String login_email) throws Exception {
		DanggeunDTO danggeunDTO = danggeunDao.select(id, login_email);
		danggeunDao.increaseViewCnt(id);
		return danggeunDTO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void modifyDangguen(DanggeunDTO danggeunDTO) throws Exception {
		danggeunDTO.setWriter_name(dangMemberDao.select(danggeunDTO.getWriter_email()).getName());
		danggeunDTO.setType_name(danggeunTypeDao.select(danggeunDTO.getType_id()).getName());
		danggeunDTO.setSido_name(sidoDao.select(danggeunDTO.getSido_code()).getSido_name());
		danggeunDTO.setSigoon_name(sigoonDao.select(danggeunDTO.getSigoon_code()).getSigoon_name());
		danggeunDTO.setDong_name(dongDao.select(danggeunDTO.getDong_code()).getDong_name());
		danggeunDao.update(danggeunDTO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void registerDanggeun(DanggeunDTO danggeunDTO) throws Exception {
		danggeunDTO.setWriter_name(dangMemberDao.select(danggeunDTO.getWriter_email()).getName());
		danggeunDTO.setType_name(danggeunTypeDao.select(danggeunDTO.getType_id()).getName());
		danggeunDTO.setSido_name(sidoDao.select(danggeunDTO.getSido_code()).getSido_name());
		danggeunDTO.setSigoon_name(sigoonDao.select(danggeunDTO.getSigoon_code()).getSigoon_name());
		danggeunDTO.setDong_name(dongDao.select(danggeunDTO.getDong_code()).getDong_name());
		danggeunDao.insert(danggeunDTO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void removeDanggeun(Integer id) throws Exception {
		danggeunDao.delete(id);
		zzimDanggeunDao.deleteAllByDanggeunId(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void pushZzim(String eamil, Integer id) throws Exception {
		zzimDanggeunDao.insert(new ZzimDanggeunDTO(eamil, id));
		danggeunDao.updateZzimCnt(id, 1);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void cancelZzim(String eamil, Integer id) throws Exception {
		zzimDanggeunDao.delete(new ZzimDanggeunDTO(eamil, id));
		danggeunDao.updateZzimCnt(id, -1);
	}

	@Override
	public List<DanggeunTypeDTO> getTypeList() throws Exception {
		return danggeunTypeDao.selectAll();
	}

}
