package com.covidchecklist.app.service;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidchecklist.app.entities.Survey;

@RestController
public class CovidService {

	// Fetches all the response from the database and sends to client 
	@GetMapping("/getAllData")
	public void getAllData()
	{
	
	}
	
	// Fetches all the questions from the database and sends to client 
	@GetMapping("/getAllQuestion")
	public List<Survey> getAllQuestions()
	{
		return null;
	}
	
	// Add survey record in database 
	@PostMapping("/add")
	public void addCovid19RecordToDB()
	{
		
	}
}