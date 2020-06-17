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

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Survey {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_id")
	Integer surveyId; 
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
	Employee employee;
	
	@Column(name = "date", nullable = false)
	Date date;
	
	@OneToMany(mappedBy = "survey_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SurveyDetails> surveyDetails;
}
