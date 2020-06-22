package com.covidchecklist.app.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covidchecklist.app.entities.AnswerOptions;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOptions, Integer> {

	@Cacheable(value = "optionCache", key = "#option", condition = "#option!=null")
	AnswerOptions findByOption(String option);

	@Cacheable(value = "optionIdCache", key = "#optionId", condition = "#optionId!=null")
	AnswerOptions findByOptionId(Integer answerId);

}
