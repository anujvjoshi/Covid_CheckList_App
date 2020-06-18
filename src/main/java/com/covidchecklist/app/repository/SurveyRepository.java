package com.covidchecklist.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covidchecklist.app.entities.Survey;

@Repository
public interface SurveyRepository  extends JpaRepository<Survey, Integer> {
	
}