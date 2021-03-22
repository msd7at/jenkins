package com.nagarro.advancejava.assignment.employeemanagerclient.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class InitWeb extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ConfigrationAppDao.class , AppConfigService.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ConfigureServlet.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
