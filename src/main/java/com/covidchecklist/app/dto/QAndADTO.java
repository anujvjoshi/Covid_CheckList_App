package com.covidchecklist.app.dto;

import lombok.Data;

@Data
public class QAndADTO {

	private Integer questionAnswerId;

	private String question;

	private Integer questionId;

	private String option1;

	private Integer option1Id;

	private String option2;

	private Integer option2Id;
	
}
