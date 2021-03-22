package com.nagarro.advancejava.assignment.employeemanagerclient.implementdao;

import org.hibernate.Session;

import com.nagarro.advancejava.assignment.employeemanagerclient.config.HibernateConfig;
import com.nagarro.advancejava.assignment.employeemanagerclient.dao.HRManagerDao;
import com.nagarro.advancejava.assignment.employeemanagerclient.dto.HRManager;

public class DaoImpHRManager implements HRManagerDao {
	
	public HRManager getHRManager(String hrmId) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		
		HRManager hrmObject = session.get(HRManager.class, hrmId);
		
		session.close();
		
		return hrmObject;
	}

}
