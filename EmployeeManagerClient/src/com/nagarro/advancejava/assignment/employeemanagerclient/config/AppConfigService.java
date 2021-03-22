package com.nagarro.advancejava.assignment.employeemanagerclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nagarro.advancejava.assignment.employeemanagementclient.services.EmployeeMangementService;
import com.nagarro.advancejava.assignment.employeemanagementclient.services.Sign;

@Configuration
public class AppConfigService {

	@Bean
	public Sign getSignService() {
		return new Sign();
	}

	@Bean
	public EmployeeMangementService getEmpMangService() {
		return new EmployeeMangementService();
	}
	
}
