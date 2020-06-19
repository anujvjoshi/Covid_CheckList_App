package com.covidchecklist.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidchecklist.app.dto.QAndADTO;
import com.covidchecklist.app.service.QuestionOptionService;

@RestController
public class QuestionOptionController {

	@Autowired
	QuestionOptionService questionOptionService;

	@GetMapping("/getAllQuestionsWithOptions")
	public List<QAndADTO> getAllQuestionsWithOptions() {
		return questionOptionService.getAllQuestionsWithOptions();
	}

}