package com.test2.dao;

import java.util.List;

import com.FrameWrkFullPrject.model.StudentDetails;

public interface StudentDetailsDao {

	List getDeptName(String name);

	List getSubName(String sub, String deptName);

	String onSavingData(StudentDetails stud);

	List getStudSlNo(String slno);

	StudentDetails getSlDetails(String slNo);

	List<StudentDetails> getTableData();

	String deleteData(StudentDetails stud);

}
