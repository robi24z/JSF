package com.test2.service;

import java.util.List;

import com.FrameWrkFullPrject.model.StudentDetails;

public interface StudentDetailsService {

	List selectAllDept(String name);

	List selectAllSub(String sub, String deptName);

	String onSavingData(StudentDetails stud);

	List selectAllStudent(String slno);

	StudentDetails selectSlDetails(String slNo);

	List<StudentDetails> selectTableData();

	String deleting(StudentDetails stud);

}
