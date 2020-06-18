package com.covidchecklist.app.service;

import java.util.List;

import com.covidchecklist.app.entities.Employee;

public interface CovidSurveyService {

	void getEntireSurveyData();
	
	List<Employee> getAllEmployeeDetails();
	
	void saveEmployeesToDB();
}
