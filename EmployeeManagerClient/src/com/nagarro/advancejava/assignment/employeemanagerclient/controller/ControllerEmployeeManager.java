package com.nagarro.advancejava.assignment.employeemanagerclient.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.nagarro.advancejava.assignment.employeemanagementclient.services.EmployeeMangementService;
import com.nagarro.advancejava.assignment.employeemanagerclient.dto.Employee;

@Controller
public class ControllerEmployeeManager {

	final static Logger LOG = Logger.getLogger(ControllerEmployeeManager.class);

	@Autowired
	private EmployeeMangementService employeeMangementService;

	@RequestMapping("Upload")
	public ModelAndView upload(@RequestParam("empName") String empName, @RequestParam("empLoc") String empLoc,
			@RequestParam("empMail") String empMail, @RequestParam("empDOB") String empDOB,
			@RequestParam("hrmId") String hrmId) {
		LOG.info("Recieved Employee Upload Request by : " + hrmId + " for employee : " + empName);

		ModelAndView mv = new ModelAndView();

		Employee emp = new Employee();
		emp.setEmpName(empName);
		emp.setEmpLoc(empLoc);
		emp.setEmpMail(empMail);
		emp.setEmpDOB(empDOB);

		employeeMangementService.add(emp);

		List<Employee> employees = employeeMangementService.getEmployees();
		mv.addObject("employees", employees);

		mv.addObject("hrmId", hrmId);
		mv.setViewName("home");
		return mv;
	}

	@RequestMapping("Update")
	public ModelAndView update(@RequestParam("empCode") int empCode, @RequestParam("empName") String empName,
			@RequestParam("empLoc") String empLoc, @RequestParam("empMail") String empMail,
			@RequestParam("empDOB") String empDOB, @RequestParam("hrmId") String hrmId) {
		LOG.info("Recieved Employee Update Request by : " + hrmId + " for employee : " + empCode);

		ModelAndView mv = new ModelAndView();

		Employee emp = new Employee();
		emp.setEmpCode(empCode);
		emp.setEmpName(empName);
		emp.setEmpLoc(empLoc);
		emp.setEmpMail(empMail);
		emp.setEmpDOB(empDOB);

		employeeMangementService.update(emp);

		List<Employee> employees = employeeMangementService.getEmployees();
		mv.addObject("employees", employees);

		mv.addObject("hrmId", hrmId);
		mv.setViewName("home");
		return mv;
	}

	@RequestMapping("DeleteEmployee")
	public ModelAndView delete(@RequestParam("empCode") int empCode, @RequestParam("hrmId") String hrmId) {
		LOG.info("Recieved Employee Delete Request by : " + hrmId + " for employee : " + empCode);

		ModelAndView mv = new ModelAndView();

		employeeMangementService.delete(empCode);

		List<Employee> employees = employeeMangementService.getEmployees();
		mv.addObject("employees", employees);

		mv.addObject("hrmId", hrmId);
		mv.setViewName("home");

		return mv;
	}

	@RequestMapping("EditEmployee")
	public ModelAndView Edit(@RequestParam("empCode") int empCode, @RequestParam("hrmId") String hrmId) {
		LOG.info("Recieved Employee Edit Request by : " + hrmId + " for employee : " + empCode);

		ModelAndView mv = new ModelAndView();

		Employee emp = employeeMangementService.get(empCode);

		mv.addObject("employee", emp);

		mv.addObject("hrmId", hrmId);
		mv.setViewName("edit");

		return mv;
	}

	@RequestMapping("Download")
	public void downloadEmployeeList(HttpServletRequest request, HttpServletResponse response) {
		LOG.info("Recieved Download Employees Request. ");

		List<Employee> employees = employeeMangementService.getEmployees();

		String csvFileName = "employees.csv";
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
		response.setHeader(headerKey, headerValue);
		ICsvBeanWriter csvWriter;

		try {
			csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

			String[] headers = new String[] { "EmpCode", "EmpName", "EmpLoc", "EmpMail", "EmpDOB" };

			csvWriter.writeHeader(headers);
			for (Employee employee : employees) {
				csvWriter.write(employee, headers);
			}
			csvWriter.close();
		} catch (IOException ioexception) {
			LOG.error("Error in processing download employees request. Error : " + ioexception);
		} 

		LOG.info("Download employees successfully processed.");
	}
}
