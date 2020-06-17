package com.covidchecklist.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class QAndA {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_id")
	private Integer questionId;
	@Column(name = "answer_id", nullable = false)
	private Integer answerId;
	@Column(name = "question", nullable = false)
	private String question;
	@Column(name = "option1", nullable = false)
	private String option1;
	@Column(name = "option2", nullable = false)
	private String option2;
}
