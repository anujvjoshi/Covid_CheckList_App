package com.covidchecklist.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
public class SurveyDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_details_id")
	private Integer surveyDetailsId;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "survey_id", nullable = false)
	private Survey surveyId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "que_id", nullable = false)
	private Question questionId;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "ans_id",referencedColumnName = "option_id", nullable = false)
	private AnswerOptions  answerId;

}
