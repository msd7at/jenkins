package com.nagarro.advancejava.assignment.restcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.advancejava.assignment.restcrud.entity.Employee;
import com.nagarro.advancejava.assignment.restcrud.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@PostMapping("/addemployee")
	public Employee addEmployee(@RequestBody Employee employee) {
		return service.saveEmployee(employee);
	}

	@GetMapping("/employees")
	public List<Employee> findAllEmployees() {
		return service.getEmployees();
	}

	@GetMapping("/employee/{id}")
	public Employee findEmployeeById(@PathVariable int id) {
		return service.getEmployeeById(id);
	}

	@PutMapping("/update")
	public Employee updateEmployee(@RequestBody Employee employee) {
		return service.updateEmployee(employee);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		return service.deleteEmployee(id);
	}

}
