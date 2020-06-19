package com.covidchecklist.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class QAndA {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "qa_id")
	private Integer questionOptionId;

	@ManyToOne
	@JoinColumn(name = "que_id", nullable = false)
	private Question question;

	@ManyToOne
	@JoinColumn(name = "option1", referencedColumnName = "option_id", nullable = false)
	private AnswerOptions option1;

	@ManyToOne
	@JoinColumn(name = "option2", referencedColumnName = "option_id", nullable = false)
	private AnswerOptions option2;
}
