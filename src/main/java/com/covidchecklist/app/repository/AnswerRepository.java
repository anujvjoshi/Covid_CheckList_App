package com.covidchecklist.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covidchecklist.app.entities.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}
