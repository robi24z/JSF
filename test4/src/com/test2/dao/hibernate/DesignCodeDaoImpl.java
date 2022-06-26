package com.test2.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.FrameWrkFullPrject.model.DesignCode;
import com.test2.dao.DesignCodeDao;

public class DesignCodeDaoImpl implements DesignCodeDao {
	public SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List getAllDesign(String name) {
		// System.out.println("======DAo========");
		Session session = getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List styleList = new ArrayList();
		try {
			styleList = session.createSQLQuery("SELECT DSGNSTYL FROM DESIGNCODE WHERE DSGNSTYL LIKE'" + name + "%'")
					.list();
			if (styleList.isEmpty()) {
				styleList = session.createSQLQuery("SELECT DSGNSTYL FROM DESIGNCODE").list();
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return styleList;
	}

	@Override
	public List getOrnmntName(String ornmnt, String dsgnStyl) {
		// System.out.println("=====Dao======");
		Session session = getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List dsgnId = new ArrayList();
		List ornmntName = new ArrayList();
		try {
			dsgnId = session.createSQLQuery("SELECT DSGNID FROM DESIGNCODE WHERE DSGNSTYL='" + dsgnStyl + "'").list();

			Long id = Long.parseLong(dsgnId.get(0) + "");
			// System.out.println("id=======" + id);
			ornmntName = session.createSQLQuery(
					"SELECT ORNMNTNAME FROM ORNAMENT WHERE DSGNID=" + id + " AND ORNMNTNAME LIKE '" + ornmnt + "%'")
					.list();
			// System.out.println("ornmntName===" + ornmntName);
			if (ornmntName.isEmpty()) {
				ornmntName = session.createSQLQuery("SELECT ORNMNTNAME FROM ORNAMENT").list();
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		return ornmntName;
	}

	@Override
	public String onSave(DesignCode designCode) {

		Session session = getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		String savedOrNot = "";
		try {
			List ornmntId = session
					.createSQLQuery("SELECT ORNMNTID FROM ORNAMENT WHERE ORNMNTNAME='" + designCode.getOrnament() + "'")
					.list();
			designCode.setOrnmntId(Long.parseLong(ornmntId.get(0) + ""));
			if (designCode.getOrnmntDataId() == null) {
				session.save(designCode);
				savedOrNot = "========Saved Successfully========";
			} else {
				// System.out.println("Daooooooooooo");
				session.saveOrUpdate(designCode);
				savedOrNot = "========Editted Successfully========";
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			savedOrNot = "========Saving Failed========";
		}
		return savedOrNot;
	}

	@Override
	public List<DesignCode> onTableDisplay() {
		Session session = getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<DesignCode> table4Display = new ArrayList<DesignCode>();
		List data = new ArrayList();
		try {
			data = session
					.createSQLQuery(
							"SELECT OD1.DSGNCODE,DC1.DSGNSTYL,O1.ORNMNTNAME,OD1.CRPT,OD1.ORNMNTID,OD1.ORNMNTDATAID FROM ORNMNTDATA OD1 INNER JOIN ORNAMENT O1 ON OD1.ORNMNTID=O1.ORNMNTID INNER JOIN DESIGNCODE DC1 ON DC1.DSGNID=O1.DSGNID")
					.list();
			Object[] table = null;
			DesignCode designCode = new DesignCode();
			if (data != null && data.size() > 0) {
				for (int i = 0; i < data.size(); i++) {
					table = (Object[]) data.get(i);
					designCode = new DesignCode();
					designCode.setDsgnCode(table[0] + "");
					designCode.setDsgnStyle(table[1] + "");
					designCode.setOrnament(table[2] + "");
					designCode.setCrpt(Double.parseDouble(table[3] + ""));
					designCode.setOrnmntId(Long.parseLong(table[4] + ""));
					designCode.setOrnmntDataId(Long.parseLong(table[5] + ""));
					table4Display.add(designCode);
				}
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return table4Display;
	}

	@Override
	public List getAllDsgCode(String dsgCode) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List dsgCodeList = new ArrayList();
		try {
			dsgCodeList = session
					.createSQLQuery("SELECT DSGNCODE FROM ORNMNTDATA WHERE DSGNCODE LIKE'" + dsgCode + "%'").list();
			if (dsgCodeList.isEmpty()) {
				dsgCodeList = session.createSQLQuery("SELECT DSGNCODE FROM ORNMNTDATA").list();
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return dsgCodeList;
	}

	@Override
	public DesignCode getSltdDsgDtls(String dsgCde) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		DesignCode dgCode = new DesignCode();
		List stylAndOrnmnt = new ArrayList();
		try {
			List dgCd = session.createSQLQuery("SELECT DSGNCODE FROM ORNMNTDATA WHERE DSGNCODE LIKE'" + dsgCde + "%'")
					.list();
			String code = (String) dgCd.get(0);
			stylAndOrnmnt = session.createSQLQuery(
					"SELECT OD1.DSGNCODE,D1.DSGNSTYL,O1.ORNMNTNAME,OD1.CRPT,OD1.ORNMNTID,OD1.ORNMNTDATAID FROM ORNAMENT O1 INNER JOIN DESIGNCODE D1 ON D1.DSGNID=O1.DSGNID INNER JOIN ORNMNTDATA OD1 ON OD1.DSGNCODE='"
							+ code + "'")
					.list();
			Object[] sAndOrName = (Object[]) stylAndOrnmnt.get(0);
			dgCode.setDsgnCode(sAndOrName[0] + "");
			dgCode.setDsgnStyle(sAndOrName[1] + "");
			dgCode.setOrnament(sAndOrName[2] + "");
			dgCode.setCrpt(Double.parseDouble(sAndOrName[3] + ""));
			dgCode.setOrnmntId(Long.parseLong(sAndOrName[4] + ""));
			dgCode.setOrnmntDataId(Long.parseLong(sAndOrName[5] + ""));
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return dgCode;
	}

	@Override
	public String onDeleting(DesignCode designCode) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		String dlt = "";
		try {
			session.delete(designCode);
			dlt = "Deleted Successfully";
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			dlt = "Deletion Failed";
			transaction.rollback();
		}
		return dlt;
	}

	@Override
	public String checkDsgCode(String dsCode) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List dsgCdList = new ArrayList();
		String check = "";
		try {
			dsgCdList = session.createSQLQuery("SELECT DSGNCODE FROM ORNMNTDATA WHERE DSGNCODE='" + dsCode + "'")
					.list();
			if (!dsgCdList.isEmpty()) {
				check = "Design Code Already Exist";
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return check;
	}

}
