package com.test2.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.FrameWrkFullPrject.model.StudentDetails;
import com.test2.dao.StudentDetailsDao;

public class StudentDetailsDaoImpl implements StudentDetailsDao {
	public SessionFactory sessionfactory;

	public SessionFactory getSessionfactory() {
		return sessionfactory;
	}

	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}

	public List getDeptName(String name) {
		Session session = getSessionfactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List deptName = new ArrayList();
		try {
			deptName = session
					.createSQLQuery("SELECT DEPARTMENTNAME FROM DEPARTMENT WHERE DEPARTMENTNAME LIKE '" + name + "%'")
					.list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return deptName;
	}

	public List getSubName(String sub, String deptName) {
		Session session = getSessionfactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List subjectName = new ArrayList();
		List deptId = new ArrayList();
		try {
			deptId = session
					.createSQLQuery("SELECT DEPARTMENTID FROM DEPARTMENT WHERE DEPARTMENTNAME='" + deptName + "'")
					.list();

			Object id = deptId.get(0);
			subjectName = session.createSQLQuery(
					"SELECT SUBJECTNAME FROM SUBJECT WHERE DEPTID=" + id + " AND SUBJECTNAME LIKE '" + sub + "%'")
					.list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return subjectName;
	}

	public String onSavingData(StudentDetails stud) {

		Session session = getSessionfactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		String savedOrNot = "";
		try {
			List subid = session
					.createSQLQuery("SELECT SUBJECTID FROM SUBJECT WHERE SUBJECTNAME='" + stud.getSubject() + "'")
					.list();
			stud.setSubjectId(Long.parseLong(subid.get(0) + ""));
			if (stud.getStudentId() == null) {
				session.save(stud);
				savedOrNot = "==========Saved Successfully==========";
			} else {
				session.saveOrUpdate(stud);
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			savedOrNot = "========Saving Failed========";
		}
		return savedOrNot;
	}

	public List getStudSlNo(String slno) {
		Session session = getSessionfactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List studSlNo = new ArrayList();
		try {
			studSlNo = session.createQuery("SELECT s.slno FROM StudentDetails s WHERE s.slno LIKE '" + slno + "%'")
					.getResultList();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return studSlNo;
	}

	public StudentDetails getSlDetails(String slNo) {
		Session session = getSessionfactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		StudentDetails studentdetailsobj = new StudentDetails();
		List dAndSName = new ArrayList();
		try {
			studentdetailsobj = (StudentDetails) session
					.createQuery("SELECT s FROM StudentDetails s WHERE s.slno='" + slNo + "'").uniqueResult();
			dAndSName = session.createSQLQuery(
					"SELECT S1.SUBJECTNAME,D1.DEPARTMENTNAME FROM SUBJECT S1 INNER JOIN DEPARTMENT D1 ON S1.DEPTID=D1.DEPARTMENTID WHERE S1.SUBJECTID='"
							+ studentdetailsobj.getSubjectId() + "'")
					.list();
			String subname = dAndSName.toString();
			Object[] sname = (Object[]) dAndSName.get(0);
			studentdetailsobj.setSubject(sname[0] + "");
			studentdetailsobj.setDepartment(sname[1] + "");
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return studentdetailsobj;
	}

	@Override
	public List<StudentDetails> getTableData() {
		Session session = getSessionfactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<StudentDetails> DisplayInTable = new ArrayList<StudentDetails>();
		List tableData = new ArrayList();
		try {
			tableData = session
					.createSQLQuery(
							"SELECT SD1.SLNO,SD1.NAME,S1.SUBJECTNAME,D1.DEPARTMENTNAME,SD1.AGE,S1.SUBJECTID,SD1.STUDENTID FROM STUDENTDETAILS SD1 INNER JOIN SUBJECT S1 ON SD1.SUBJECTID = S1.SUBJECTID INNER JOIN DEPARTMENT D1 ON D1.DEPARTMENTID=S1.DEPTID")
					.list();
			Object[] tableDisplay = null;
			StudentDetails stud = new StudentDetails();
			if (tableData != null && tableData.size() > 0) {
				for (int i = 0; i < tableData.size(); i++) {
					tableDisplay = (Object[]) tableData.get(i);
					stud = new StudentDetails();
					stud.setSlno(tableDisplay[0] + "");
					stud.setName(tableDisplay[1] + "");
					stud.setDepartment(tableDisplay[2] + "");
					stud.setSubject(tableDisplay[3] + "");
					stud.setAge(Long.parseLong(tableDisplay[4] + ""));
					stud.setSubjectId(Long.parseLong(tableDisplay[5] + ""));
					stud.setStudentId(Long.parseLong(tableDisplay[6] + ""));
					DisplayInTable.add(stud);
				}
			}

			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return DisplayInTable;
	}

	@Override
	public String deleteData(StudentDetails stud) {
		Session session = getSessionfactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		String dlt = "";
		try {
			session.delete(stud);
			dlt = "========Deleted Successfully========";
			transaction.commit();
		} catch (Exception e) {
			dlt="========Deletion failed========";
			e.printStackTrace();
			transaction.rollback();
		}
		return dlt;
	}

}
