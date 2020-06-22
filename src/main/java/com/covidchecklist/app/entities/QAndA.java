package com.covidchecklist.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class QAndA {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "qa_id")
	private Integer questionOptionId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "que_id", nullable = false)
	private Question question;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "option1", referencedColumnName = "option_id", nullable = false)
	private AnswerOptions option1;

	@ManyToOne
	@JoinColumn(name = "option2", referencedColumnName = "option_id", nullable = false)
	private AnswerOptions option2;
}
