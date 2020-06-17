package com.covidchecklist.app.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QAndA {

	int questionId;

	int answerId;

	String question;

	String option1;

	String option2;
}
