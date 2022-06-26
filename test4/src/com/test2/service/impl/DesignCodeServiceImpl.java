package com.test2.service.impl;

import java.util.List;

import com.FrameWrkFullPrject.model.DesignCode;
import com.test2.dao.DesignCodeDao;
import com.test2.service.DesignCodeService;

public class DesignCodeServiceImpl implements DesignCodeService {
	public DesignCodeDao designCodeDao;

	public DesignCodeDao getDesignCodeDao() {
		return designCodeDao;
	}

	public void setDesignCodeDao(DesignCodeDao designCodeDao) {
		this.designCodeDao = designCodeDao;
	}

	@Override
	public List selectAllDesign(String name) {
		return designCodeDao.getAllDesign(name);
	}

	@Override
	public List selectOrnaments(String ornmnt, String dsgnStyl) {

		return designCodeDao.getOrnmntName(ornmnt, dsgnStyl);
	}

	@Override
	public String onSavingData(DesignCode designCode) {
		// TODO Auto-generated method stub
		return designCodeDao.onSave(designCode);
	}

	@Override
	public List<DesignCode> getTableData() {
		// TODO Auto-generated method stub
		return designCodeDao.onTableDisplay();
	}

	@Override
	public List selectAllDsgCode(String dsgCode) {
		// TODO Auto-generated method stub
		return designCodeDao.getAllDsgCode(dsgCode);
	}

	@Override
	public DesignCode getSelectedDsgDtl(String dsgCde) {

		return designCodeDao.getSltdDsgDtls(dsgCde);
	}

	@Override
	public String onDelete(DesignCode designCode) {
		// TODO Auto-generated method stub
		return designCodeDao.onDeleting(designCode);
	}

	@Override
	public String selectDsgCode(String dsCode) {
		
		return designCodeDao.checkDsgCode(dsCode);
	}


}
