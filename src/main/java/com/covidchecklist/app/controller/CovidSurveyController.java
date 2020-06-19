package com.covidchecklist.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.covidchecklist.app.dto.SurveyDTO;
import com.covidchecklist.app.dto.SurveyDetailsDTO;
import com.covidchecklist.app.service.CovidSurveyService;

@RestController
public class CovidSurveyController {

	@Autowired
	CovidSurveyService	service;
	
	// Fetches all the response from the database and sends to client 
	@GetMapping("/getEntireSurveyData")
	public List<SurveyDTO> getEntireSurveyData()
	{
		return service.getEntireSurveyData();
	}
	
	// Add survey record in database 
	@PostMapping("/submitSurvey")
	public void submitCovid19RecordToDB( @RequestBody SurveyDTO surveyDto)
	{
		service.saveEntireSurveyData(surveyDto);
	}
	
	@ResponseBody
	@GetMapping(value = "/getSurveyDetails/{id}")
	public List<SurveyDetailsDTO> validateEmp(@PathVariable("id") Integer surveyId) {
		return service.getSurveyDetails(surveyId);
	}
}