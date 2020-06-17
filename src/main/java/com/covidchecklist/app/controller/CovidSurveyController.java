package com.covidchecklist.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidchecklist.app.entities.Survey;
import com.covidchecklist.app.service.CovidSurveyService;

@RestController
public class CovidSurveyController {

	@Autowired
	CovidSurveyService	service;
	
	// Fetches all the response from the database and sends to client 
	@GetMapping("/getEntireSurveyData")
	public void getEntireSurveyData()
	{
		service.getEntireSurveyData();
	}
	
	// Fetches all the questions from the database and sends to client 
	@GetMapping("/getAllQuestionWithOptions")
	public List<Survey> getAllQuestionsWithOptions()
	{
		return null;
	}
	
	// Add survey record in database 
	@PostMapping("/submitSurvey")
	public void submitCovid19RecordToDB()
	{
		
	}
}