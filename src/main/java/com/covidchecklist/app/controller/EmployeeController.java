package com.covidchecklist.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.covidchecklist.app.dto.Response;
import com.covidchecklist.app.service.EmployeeService;
import com.covidchecklist.app.service.QuestionOptionService;

@RestController
public class EmployeeController {

	@Autowired
	QuestionOptionService questionOptionService;

	@Autowired
	EmployeeService employeeService;

	@GetMapping(value = "/validateEmp/{id}")
	public ResponseEntity<Response> validateEmp(@PathVariable("id") String empId) {

		boolean value = employeeService.validateEmployee(empId);

		Response response = new Response();
		response.setResponseData(empId);
		response.setStatus(value ? Response.SUCCESS_STATUS : Response.FAIL_STATUS);

		return ResponseEntity.ok(response);

	}

	@GetMapping("/saveDataToDB")
	public ResponseEntity<Response> saveEmployeesToDB() {

		questionOptionService.saveQuestionsToDB();
		employeeService.saveEmployeesToDB();

		Response response = new Response();
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/getEmpData")
	public ResponseEntity<Response> getAllEmpDetails() {

		Response response = new Response();
		response.setResponseData(employeeService.getAllEmployeeDetails());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

}