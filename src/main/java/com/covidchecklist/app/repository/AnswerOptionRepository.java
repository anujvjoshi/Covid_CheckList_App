package com.covidchecklist.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covidchecklist.app.entities.AnswerOptions;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOptions, Integer> {

	AnswerOptions findByOption(String option);

	AnswerOptions findByOptionId(Integer answerId);

}
