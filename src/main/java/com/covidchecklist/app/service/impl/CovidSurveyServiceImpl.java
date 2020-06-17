package com.covidchecklist.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidchecklist.app.repository.CovidSurveyRepository;
import com.covidchecklist.app.service.CovidSurveyService;

@Service
public class CovidSurveyServiceImpl implements CovidSurveyService {
	
	@Autowired CovidSurveyRepository covidSurveyRepository;

	@Override
	public void getEntireSurveyData() {
		covidSurveyRepository.findAll();
	}
}
