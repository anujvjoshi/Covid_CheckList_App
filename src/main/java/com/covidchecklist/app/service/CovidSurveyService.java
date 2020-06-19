package com.covidchecklist.app.service;

import java.util.List;

import com.covidchecklist.app.dto.EmployeeDTO;

public interface CovidSurveyService {

	void getEntireSurveyData();
	
	List<EmployeeDTO> getAllEmployeeDetails();
	
	void saveEmployeesToDB();
	
	void saveQuestionsToDB();
	
	boolean validateEmployee(String empId);
}
