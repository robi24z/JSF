package com.test2.service.impl;

import java.util.List;

import com.FrameWrkFullPrject.model.StudentDetails;
import com.test2.dao.StudentDetailsDao;
import com.test2.service.StudentDetailsService;

public class StudentDetailsServiceImpl implements StudentDetailsService {

	public StudentDetailsDao studentDetailsDao;

	public StudentDetailsDao getStudentDetailsDao() {
		return studentDetailsDao;
	}

	public void setStudentDetailsDao(StudentDetailsDao studentDetailsDao) {
		this.studentDetailsDao = studentDetailsDao;
	}

	public List selectAllDept(String name) {
		return studentDetailsDao.getDeptName(name);
	}

	@Override
	public List selectAllSub(String sub, String deptName) {
		return studentDetailsDao.getSubName(sub, deptName);
	}

	@Override
	public String onSavingData(StudentDetails stud) {
		return studentDetailsDao.onSavingData(stud);
	}

	@Override
	public List selectAllStudent(String slno) {
		return studentDetailsDao.getStudSlNo(slno);
	}

	@Override
	public StudentDetails selectSlDetails(String slNo) {
		
		return studentDetailsDao.getSlDetails(slNo);
	}

	@Override
	public List<StudentDetails> selectTableData() {
		
		return studentDetailsDao.getTableData();
	}

	@Override
	public String deleting(StudentDetails stud) {
		// TODO Auto-generated method stub
		return studentDetailsDao.deleteData(stud);
	}

}
