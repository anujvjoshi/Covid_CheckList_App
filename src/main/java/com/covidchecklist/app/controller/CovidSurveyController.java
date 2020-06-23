package com.covidchecklist.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.covidchecklist.app.dto.Response;
import com.covidchecklist.app.dto.SurveyDTO;
import com.covidchecklist.app.service.CovidSurveyService;

@RestController
public class CovidSurveyController {

	@Autowired
	CovidSurveyService service;

	// Fetches all the response from the database and sends to client
	@GetMapping("/getEntireSurveyData")
	public ResponseEntity<Response> getEntireSurveyData() {

		Response response = new Response();
		response.setResponseData(service.getEntireSurveyData());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);
	}

	// Add survey record in database
	@RequestMapping(value = "/submitSurvey", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Response> submitCovid19RecordToDB(@RequestBody SurveyDTO surveyDto) {

		Response response = new Response();
		response.setResponseData(service.saveEntireSurveyData(surveyDto));
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/getSurveyDetails/{id}")
	public ResponseEntity<Response> validateEmp(@PathVariable("id") Integer surveyId) {

		Response response = new Response();
		response.setResponseData(service.getSurveyDetails(surveyId));
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);
	}

}