package com.test2.dao;

import java.util.List;

import com.FrameWrkFullPrject.model.DesignCode;

public interface DesignCodeDao {

	List getAllDesign(String name);

	List getOrnmntName(String ornmnt, String dsgnStyl);

	String onSave(DesignCode designCode);

	List<DesignCode> onTableDisplay();

	List getAllDsgCode(String dsgCode);

	DesignCode getSltdDsgDtls(String dsgCde);

	String onDeleting(DesignCode designCode);

	String checkDsgCode(String dsCode);


}
