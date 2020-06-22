package com.covidchecklist.app.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covidchecklist.app.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	@Cacheable(value = "questionCache", key = "#question", condition = "#question!=null")
	Question findByQuestion(String question);

	@Cacheable(value = "questionIdCache", key = "#questionId", condition = "#questionId!=null")
	Question findByQuestionId(Integer questionId);

}
