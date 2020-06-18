package com.covidchecklist.app.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "que_id")
	private Integer questionId;
	
	private String question;
	
	@OneToMany(mappedBy = "questionId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SurveyDetails> surveyQuestions;
	
}
