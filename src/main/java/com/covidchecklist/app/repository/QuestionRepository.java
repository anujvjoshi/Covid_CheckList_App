package com.covidchecklist.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covidchecklist.app.entities.Question;

@Repository
public interface QuestionRepository  extends JpaRepository<Question, Integer> {

	Question findByQuestion(String question);

	Question findByQuestionId(Integer questionId);
	
}
