package com.test2.action;

import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.SelectEvent;
import com.FrameWrkFullPrject.model.StudentDetails;
import com.test2.service.StudentDetailsService;

@ManagedBean(name = "student")
@RequestScoped

public class StudentDetailsAction {

	@ManagedProperty(value = "#{StudentDetailsServiceImpl}")
	StudentDetailsService studentservice;
	StudentDetails stud = new StudentDetails();
	String deptName;
	boolean saveClick = false, searchClick = false;
	List<StudentDetails> tableData = new ArrayList<StudentDetails>();
	String valdtnMsg;

	public String getValdtnMsg() {

		return valdtnMsg;
	}

	public void setValdtnMsg(String valdtnMsg) {

		this.valdtnMsg = valdtnMsg;
	}

	public List<StudentDetails> getTableData() {
		if (tableData.isEmpty()) {
			tableData = getStudentservice().selectTableData();
		}
		return tableData;
	}

	public void setTableData(List<StudentDetails> tableData) {
		this.tableData = tableData;
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

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public StudentDetailsService getStudentservice() {
		return studentservice;
	}

	public void setStudentservice(StudentDetailsService studentservice) {
		this.studentservice = studentservice;
	}

	public StudentDetails getStud() {
		return stud;
	}

	public void setStud(StudentDetails stud) {
		this.stud = stud;
	}

	public List allDepartmentLoad(String name) {
		List deptList = getStudentservice().selectAllDept(name);
		return deptList;
	}

	public void getSelectedDept(SelectEvent event) {
		deptName = (String) event.getObject();
	}

	public List subjectNames(String sub) {
		List subName = getStudentservice().selectAllSub(sub, deptName);
		return subName;
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
		stud = new StudentDetails();
		tableData = new ArrayList<StudentDetails>();
	}

	public void onSaveClick() {
		valdtnMsg = getStudentservice().onSavingData(stud);
		onCancelClick();
	}

	public List allSlNoLoad(String slno) {
		List studList = getStudentservice().selectAllStudent(slno);
		return studList;
	}

	public void getSelectedSlNo(SelectEvent event) {
		String slNo = (String) event.getObject();
		stud = getStudentservice().selectSlDetails(slNo);
		tableData = new ArrayList<StudentDetails>();
		tableData.add(stud);
	}

	public void onDelete() {
		valdtnMsg = getStudentservice().deleting(stud);

		onCancelClick();
	}

	public void setSlNoChecking(String slno) {
		System.out.println("Slno========" + slno);
	}

}
