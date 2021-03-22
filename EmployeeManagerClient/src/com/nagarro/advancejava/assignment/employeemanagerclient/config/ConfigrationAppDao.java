package com.nagarro.advancejava.assignment.employeemanagerclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nagarro.advancejava.assignment.employeemanagerclient.dao.HRManagerDao;
import com.nagarro.advancejava.assignment.employeemanagerclient.implementdao.DaoImpHRManager;

@Configuration
public class ConfigrationAppDao {

	@Bean
	public HRManagerDao getHRManager() {
		return new DaoImpHRManager();
	}
}
