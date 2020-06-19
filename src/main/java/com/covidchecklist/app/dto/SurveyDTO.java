package com.covidchecklist.app.dto;

import java.util.List;

import lombok.Data;

@Data
public class SurveyDTO {

	Integer surveyId;

	String empName;

	String email;

	Integer empId;

	String date;

	List<SurveyDetailsDTO> surveyAnsList;

}
