package com.covidchecklist.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidchecklist.app.dto.Response;
import com.covidchecklist.app.service.QuestionOptionService;

@RestController
public class QuestionOptionController {

	@Autowired
	QuestionOptionService questionOptionService;

	@GetMapping("/getAllQuestionsWithOptions")
	public ResponseEntity<Response> getAllQuestionsWithOptions() {

		Response response = new Response();
		response.setResponseData(questionOptionService.getAllQuestionsWithOptions());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);
	}

}