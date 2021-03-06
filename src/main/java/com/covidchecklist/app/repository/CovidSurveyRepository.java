package com.covidchecklist.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covidchecklist.app.entities.QAndA;

@Repository
public interface CovidSurveyRepository  extends JpaRepository<QAndA, Integer> {
	
}
