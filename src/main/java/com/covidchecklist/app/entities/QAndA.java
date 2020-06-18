package com.covidchecklist.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	@Column(name = "qa_id")
	private Integer questionAnswerId;

	@ManyToOne
	@JoinColumn(name = "que_id", nullable = false)
	private Question question;

	@ManyToOne
	@JoinColumn(name = "option1", referencedColumnName = "ans_id", nullable = false)
	private Answer option1;

	@ManyToOne
	@JoinColumn(name = "option2", referencedColumnName = "ans_id", nullable = false)
	private Answer option2;
}
