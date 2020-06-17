package com.covidchecklist.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SurveyDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_details_id")
	private Integer surveyDetailsId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@Column(name = "survey_id", nullable = false)
	private Survey surveyId;

	@Column(name = "que_id", nullable = false)
	private Integer questioId;

	@Column(name = "and_id", nullable = false)
	private Integer answerId;

}
