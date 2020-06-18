package com.covidchecklist.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covidchecklist.app.entities.SurveyDetails;

@Repository
public interface SurveyDetailsRepository  extends JpaRepository<SurveyDetails, Integer> {
	
}
