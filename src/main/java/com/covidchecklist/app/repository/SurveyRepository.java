package com.covidchecklist.app.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covidchecklist.app.entities.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {

	@Cacheable(value = "surveyIdCache", key = "#surveyId", condition = "#surveyId!=null")
	Survey findBySurveyId(Integer surveyId);

}
