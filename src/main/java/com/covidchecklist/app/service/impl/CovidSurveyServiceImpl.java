package com.covidchecklist.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidchecklist.app.dto.SurveyDTO;
import com.covidchecklist.app.dto.SurveyDetailsDTO;
import com.covidchecklist.app.entities.Employee;
import com.covidchecklist.app.entities.Survey;
import com.covidchecklist.app.entities.SurveyDetails;
import com.covidchecklist.app.modelmapper.Covid19ModelMapper;
import com.covidchecklist.app.repository.AnswerOptionRepository;
import com.covidchecklist.app.repository.EmployeeRepository;
import com.covidchecklist.app.repository.QuestionRepository;
import com.covidchecklist.app.repository.SurveyDetailsRepository;
import com.covidchecklist.app.repository.SurveyRepository;
import com.covidchecklist.app.service.CovidSurveyService;
import com.covidchecklist.app.smtp.EmailService;

@Service
public class CovidSurveyServiceImpl implements CovidSurveyService {

	@Autowired
	SurveyRepository surveyRepository;

	@Autowired
	SurveyDetailsRepository surveyDetailsRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	AnswerOptionRepository answerOptionRepository;

	@Autowired
	EmailService emailService;

	@Autowired
	Covid19ModelMapper mapper;

	@Override
	public List<SurveyDTO> getEntireSurveyData() {
		List<Survey> survey = surveyRepository.findAll();
		return mapper.map(survey, new TypeToken<List<SurveyDTO>>() {
		}.getType());
	}

	@Override
	public SurveyDTO saveEntireSurveyData(SurveyDTO surveyDto) {

		Employee employee = employeeRepository.findByEmployeeId(String.valueOf(surveyDto.getEmpId()));

		Survey survey = new Survey();
		survey.setDate(new Date());
		survey.setEmployee(employee);

		surveyRepository.save(survey);

		List<SurveyDetails> SurveyDetailsList = new ArrayList<>();

		surveyDto.getSurveyAnsList().forEach(s -> {

			SurveyDetails surveyDetails = new SurveyDetails();
			surveyDetails.setSurveyId(survey);
			surveyDetails.setQuestionId(questionRepository.findByQuestionId(s.getQuestionId()));
			surveyDetails.setAnswerId(answerOptionRepository.findByOptionId(s.getAnswerId()));

			SurveyDetailsList.add(surveyDetails);
		});

		surveyDetailsRepository.saveAll(SurveyDetailsList);

		emailService.sendSurveyEmail(survey.getSurveyId().toString(), employee.getName(), employee.getEmail(),
				survey.getDate(), getSurveyDetails(survey.getSurveyId()));

		return mapper.map(survey, SurveyDTO.class);

	}

	@Override
	public List<SurveyDetailsDTO> getSurveyDetails(Integer surveyId) {
		Survey survey = surveyRepository.findBySurveyId(surveyId);
		List<SurveyDetails> surveyDetails = surveyDetailsRepository.findAllBySurveyId(survey);
		return mapper.map(surveyDetails, new TypeToken<List<SurveyDetailsDTO>>() {
		}.getType());
	}

	// For testing only
//	@PostConstruct
//	public void insertMockData() {
//
//		Employee emp = new Employee();
//		emp.setEmployeeId("1000");
//		emp.setName("Ganesh Muluk");
//		emp.setEmail("ganesh.muluk@sydac.com");
//
//		employeeRepository.save(emp);
//
//		Question que = new Question();
//		que.setQuestion(" Are you happy to give survey? ");
//
//		questionRepository.save(que);
//
//		Answer option1 = new Answer();
//		option1.setAnswer("Yes");
//		answerRepository.save(option1);
//
//		Answer option2 = new Answer();
//		option2.setAnswer("No");
//		answerRepository.save(option2);
//
//		Survey survey = new Survey();
//		survey.setEmployee(emp);
//		survey.setDate(new Date());
//
//		surveyRepository.save(survey);
//
//		SurveyDetails surveyDetails = new SurveyDetails();
//		surveyDetails.setSurveyId(survey);
//		surveyDetails.setQuestionId(que);
//		surveyDetails.setAnswerId(option1);
//
//		surveyDeitailsRepository.save(surveyDetails);
//
//		QAndA qAndA = new QAndA();
//
//		qAndA.setQuestion(que);
//		qAndA.setOption1(option1);
//		qAndA.setOption2(option2);
//
//		qAndARepository.save(qAndA);
//
//	}
}
