package com.covidchecklist.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.covidchecklist.app.dto.EmployeeDTO;
import com.covidchecklist.app.service.EmployeeService;
import com.covidchecklist.app.service.QuestionOptionService;

@RestController
public class EmployeeController {

	@Autowired
	QuestionOptionService questionOptionService;

	@Autowired
	EmployeeService employeeService;

	@ResponseBody
	@GetMapping(value = "/validateEmp/{id}")
	public boolean validateEmp(@PathVariable("id") String empId) {
		boolean value = employeeService.validateEmployee(empId);
		return value;
	}

	@GetMapping("/saveDataToDB")
	public void saveEmployeesToDB() {
		questionOptionService.saveQuestionsToDB();
		employeeService.saveEmployeesToDB();
	}

	@GetMapping("/getEmpData")
	public List<EmployeeDTO> getAllEmpDetails() {
		return employeeService.getAllEmployeeDetails();
	}

}