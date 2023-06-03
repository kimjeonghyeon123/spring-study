package com.earth.danggeun.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earth.danggeun.dao.DanggeunDao;
import com.earth.danggeun.dao.DanggeunTypeDao;
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
	
	public DanggeunServiceImpl(DanggeunDao danggeunDao, DanggeunTypeDao danggeunTypeDao, ZzimDanggeunDao zzimDanggeunDao) {
		this.danggeunDao = danggeunDao;
		this.danggeunTypeDao = danggeunTypeDao;
		this.zzimDanggeunDao = zzimDanggeunDao;
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
	public void modifyDangguen(DanggeunDTO danggeunDTO) throws Exception {
		danggeunDao.update(danggeunDTO);
	}

	@Override
	public void registerDanggeun(DanggeunDTO danggeunDTO) throws Exception {
		danggeunDao.insert(danggeunDTO);
	}

	@Override
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
