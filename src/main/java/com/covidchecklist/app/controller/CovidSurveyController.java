package com.covidchecklist.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.covidchecklist.app.dto.EmployeeDTO;
import com.covidchecklist.app.dto.SurveyDTO;
import com.covidchecklist.app.service.CovidSurveyService;

@RestController
public class CovidSurveyController {

	@Autowired
	CovidSurveyService	service;
	
	@ResponseBody
	@GetMapping(value = "/validateEmp/{id}")
	public boolean validateEmp(@PathVariable("id") String empId)
	{
		boolean value = service.validateEmployee(empId);
		return value;
	}
	
	
	@GetMapping("/saveDataToDB")
	public void saveEmployeesToDB()
	{
		service.saveQuestionsToDB();
		service.saveEmployeesToDB();
	}
	
	@GetMapping("/getEmpData")
	public List<EmployeeDTO> getAllEmpDetails()
	{
		return service.getAllEmployeeDetails();
	}
	
	// Fetches all the response from the database and sends to client 
	@GetMapping("/getEntireSurveyData")
	public void getEntireSurveyData()
	{
		service.getEntireSurveyData();
	}
	
	// Fetches all the questions from the database and sends to client 
	@GetMapping("/getAllQuestionWithOptions")
	public List<SurveyDTO> getAllQuestionsWithOptions()
	{
		return null;
	}
	
	// Add survey record in database 
	@PostMapping("/submitSurvey")
	public void submitCovid19RecordToDB()
	{
		
	}
}