package com.covidchecklist.app.service;

import java.util.List;

import com.covidchecklist.app.dto.QAndADTO;

public interface QuestionOptionService {

	void saveQuestionsToDB();
	
	List<QAndADTO> getAllQuestionsWithOptions();

}
