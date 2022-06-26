package com.test2.service;

import java.util.List;

import com.FrameWrkFullPrject.model.DesignCode;

public interface DesignCodeService {

	List selectAllDesign(String name);

	List selectOrnaments(String ornmnt, String dsgnStyl);

	String onSavingData(DesignCode designCode);

	List<DesignCode> getTableData();

	List selectAllDsgCode(String dsgCode);

	DesignCode getSelectedDsgDtl(String dsgCde);

	String onDelete(DesignCode designCode);

	String selectDsgCode(String dsCode);

	

}
