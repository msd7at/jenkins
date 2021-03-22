package com.nagarro.advancejava.assignment.employeemanagementclient.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.advancejava.assignment.employeemanagerclient.dto.Employee;
import com.nagarro.advancejava.assignment.employeemanagerclient.url.URL;

@Service
public class EmployeeMangementService {
	
	final static Logger LOG = Logger.getLogger(EmployeeMangementService.class);

	public List<Employee> getEmployees() {
		
			
		LOG.info("Requesting EmployeeRestService for list of employees.");

		String url = URL.GET_EMPS_URL;
		
		RestTemplate restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		ResponseEntity<List<Employee>> response = restTemplate.exchange(url , HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {});
		
		List<Employee> employees = response.getBody();

		LOG.info("Request successful. Recieved list of employees.");
		return employees;
		
		
	}

	public Employee get(int empCode) {
		LOG.info("Requesting EmployeeRestService for employee : " + empCode);
		
		String url = URL.GET_EMP_URL + empCode;
		
		RestTemplate restTemplate = new RestTemplate();
		
		LOG.info("Request successful. Recieved employee : " + empCode);
		return restTemplate.getForObject(url, Employee.class);
	}

	public void add(Employee emp) {
		LOG.info("Requesting EmployeeRestService to add new employee : " + emp.getEmpCode());
		
		String url = URL.UPLD_EMP_URL;

		RestTemplate restTemplate = new RestTemplate();
		 
		HttpEntity<Employee> request = new HttpEntity<Employee>(emp);
		restTemplate.postForObject(url, request, Employee.class);
		
		LOG.info("Request successful. Added employee : " + emp.getEmpCode());
	}

	public void update(Employee emp) {
		LOG.info("Requesting EmployeeRestService to update employee : " + emp.getEmpCode());
		
		String url = URL.UPDT_EMP_URL ;

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Employee> request = new HttpEntity<Employee>(emp);
		
		restTemplate.put(url, request);
		LOG.info("Request successful. Updated employee : " + emp.getEmpCode());
	}
	
	public void delete(int empCode) {
		LOG.info("Requesting EmployeeRestService to delete employee : "+ empCode);
		
		String url = URL.DLT_EMP_URL + empCode;
		 
	    RestTemplate restTemplate = new RestTemplate();
	    
	    restTemplate.delete(url);
	    
	    LOG.info("Request successful. Deleted employee : " + empCode);
	}

}
