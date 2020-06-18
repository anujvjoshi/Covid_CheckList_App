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
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ans_id")
	private Integer answerId;

	@Column(name = "answer", nullable = false)
	private String answer;

	@OneToMany(mappedBy = "answerId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SurveyDetails> surveyAnswer;

	@OneToMany(mappedBy = "option1", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<QAndA> option1;

	@OneToMany(mappedBy = "option2", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<QAndA> option2;
}
