package com.nagarro.advancejava.assignment.employeemanagementclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.advancejava.assignment.employeemanagerclient.dao.HRManagerDao;
import com.nagarro.advancejava.assignment.employeemanagerclient.dto.HRManager;

@Service
public class Sign {

	@Autowired
	private HRManagerDao hrmDao;

	public boolean validateHRManager(String hrmId, String hrmPswd) {

		HRManager hrmObject = hrmDao.getHRManager(hrmId);

		return hrmObject != null && hrmObject.getHrPswd().equals(hrmPswd);
	}

}
