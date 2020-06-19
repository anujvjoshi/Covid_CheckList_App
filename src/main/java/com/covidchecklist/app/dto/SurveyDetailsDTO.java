package com.covidchecklist.app.dto;

import lombok.Data;

@Data
public class SurveyDetailsDTO {

	private Integer surveyDetailsId;

	private Integer surveyId;

	private Integer questionId;

	private String question;

	private Integer answerId;

	private String answer;

}
