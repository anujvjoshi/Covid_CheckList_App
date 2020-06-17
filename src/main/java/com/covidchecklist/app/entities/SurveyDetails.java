package com.covidchecklist.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SurveyDetails {

	@Column(name = "sruvey_id", nullable = false)
	Integer surveyId;
	
	@Column(name = "que_id", nullable = false)
	Integer questioId;
	
	@Column(name = "and_id", nullable = false)
	Integer answerId;
}
