package com.test2.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.SelectEvent;

import com.FrameWrkFullPrject.model.DesignCode;
import com.test2.service.DesignCodeService;

@ManagedBean(name = "design")
@RequestScoped
public class DesignCodeAction {
	@ManagedProperty(value = "#{DesignCodeServiceImpl}")
	DesignCodeService designService;
	DesignCode designCode = new DesignCode();
	boolean saveClick = false, searchClick = false;
	String dsgnStyl, valdtn;
	List<DesignCode> tableOnLoad = new ArrayList<DesignCode>();

	public List<DesignCode> getTableOnLoad() {
		if (tableOnLoad.isEmpty()) {
			tableOnLoad = getDesignService().getTableData();
		}
		return tableOnLoad;
	}

	public void setTableOnLoad(List<DesignCode> tableOnLoad) {
		this.tableOnLoad = tableOnLoad;
	}

	public String getValdtn() {
		return valdtn;
	}

	public void setValdtn(String valdtn) {
		this.valdtn = valdtn;
	}

	public String getDsgnStyl() {
		return dsgnStyl;
	}

	public void setDsgnStyl(String dsgnStyl) {
		this.dsgnStyl = dsgnStyl;
	}

	public boolean isSaveClick() {
		return saveClick;
	}

	public void setSaveClick(boolean saveClick) {
		this.saveClick = saveClick;
	}

	public boolean isSearchClick() {
		return searchClick;
	}

	public void setSearchClick(boolean searchClick) {
		this.searchClick = searchClick;
	}

	public DesignCode getDesignCode() {
		return designCode;
	}

	public void setDesignCode(DesignCode designCode) {
		this.designCode = designCode;
	}

	public DesignCodeService getDesignService() {
		return designService;
	}

	public void setDesignService(DesignCodeService designService) {
		this.designService = designService;
	}

	public void onSearchClick() {
		saveClick = true;
		searchClick = true;
	}

	public void onEditClick() {
		saveClick = false;
		searchClick = true;

	}

	public void onCancelClick() {
		saveClick = false;
		searchClick = false;
		designCode = new DesignCode();
		tableOnLoad = new ArrayList<DesignCode>();
	}
	public void onSaveClick() {
		//System.out.println("Style=========="+designCode.getDsgnStyle());
		valdtn = getDesignService().onSavingData(designCode);
		onCancelClick();
	}
	public void onDeleteClick() {
		valdtn = getDesignService().onDelete(designCode);
		onCancelClick();
	}
	public List allDesignStyle(String name) {
		List designStyle = getDesignService().selectAllDesign(name);
		return designStyle;
	}
	public void selectedStyl(SelectEvent event) {
		dsgnStyl = (String) event.getObject();
		// System.out.println("dsgnStyl===11=="+dsgnStyl);
	}
	public List ornamentList(String ornmnt) {
		List ornmntList = getDesignService().selectOrnaments(ornmnt, dsgnStyl);
		return ornmntList;
	}
	public List allDsgCodeLoad(String dsgCode) {
		List allDsgList = getDesignService().selectAllDsgCode(dsgCode);
		return allDsgList;
	}
	public void selectedDsgCde(SelectEvent event) {
		String dsgCde = (String) event.getObject();
		designCode = getDesignService().getSelectedDsgDtl(dsgCde);
		tableOnLoad = new ArrayList<DesignCode>();
		tableOnLoad.add(designCode);
	}

	public void setDsgCheck(String dsCode) {
		valdtn = getDesignService().selectDsgCode(dsCode);
	}
}
