package com.covidchecklist.app.service;

import java.util.List;

import com.covidchecklist.app.dto.SurveyDTO;
import com.covidchecklist.app.dto.SurveyDetailsDTO;

public interface CovidSurveyService {

	List<SurveyDTO> getEntireSurveyData();
	
	SurveyDTO saveEntireSurveyData(SurveyDTO surveyDto);

	List<SurveyDetailsDTO> getSurveyDetails(Integer surveyId);
	
}
