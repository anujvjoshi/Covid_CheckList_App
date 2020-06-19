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

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class AnswerOptions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "option_id")
	private Integer optionId;

	@Column(name = "ans_option", nullable = false)
	private String option;

	@OneToMany(mappedBy = "answerId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SurveyDetails> surveyAnswer;

	@OneToMany(mappedBy = "option1", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<QAndA> option1;

	@OneToMany(mappedBy = "option2", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<QAndA> option2;
}
