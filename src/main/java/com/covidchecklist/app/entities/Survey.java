package com.covidchecklist.app.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Survey {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_id")
	Integer surveyId;

	@ManyToOne
	@JoinColumn(name = "emp_id", nullable = false)
	Employee employee;

	@Column(name = "date", nullable = false)
	Date date;

	@OneToMany(mappedBy = "surveyId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SurveyDetails> surveyDetails;
}
