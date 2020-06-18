package com.covidchecklist.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidchecklist.app.entities.Employee;
import com.covidchecklist.app.repository.EmployeeRepository;
import com.covidchecklist.app.repository.SurveyRepository;
import com.covidchecklist.app.service.CovidSurveyService;

@Service
public class CovidSurveyServiceImpl implements CovidSurveyService {

	@Autowired
	SurveyRepository surveyRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ReadExcelService readExcelService;


	@Override
	public List<Employee> getAllEmployeeDetails() {
		return (List<Employee>) employeeRepository.findAll();
	}

	@Override
	public void saveEmployeesToDB() {

		List<String> dbEmployees = getAllEmployeeDetails().stream().map(e -> e.getEmployeeId())
				.collect(Collectors.toList());
		List<Employee> employees = readExcelService.readDataFromExcel().stream()
				.filter(e -> !dbEmployees.contains(e.getEmployeeId())).collect(Collectors.toList());

		employeeRepository.saveAll(employees);
	}
	

	@Override
	public void getEntireSurveyData() {
		surveyRepository.findAll();
	}

	// For testing only
	/*@PostConstruct
	public void insertMockData() {

		Employee emp = new Employee();
		emp.setEmployeeId("1000");
		emp.setName("Ganesh Muluk");
		emp.setEmail("ganesh.muluk@sydac.com");

		employeeRepository.save(emp);

		Question que = new Question();
		que.setQuestion(" Are you happy to give survey? ");

		questionRepository.save(que);

		Answer option1 = new Answer();
		option1.setAnswer("Yes");
		answerRepository.save(option1);

		Answer option2 = new Answer();
		option2.setAnswer("No");
		answerRepository.save(option2);

		Survey survey = new Survey();
		survey.setEmployee(emp);
		survey.setDate(new Date());

		surveyRepository.save(survey);

		SurveyDetails surveyDetails = new SurveyDetails();
		surveyDetails.setSurveyId(survey);
		surveyDetails.setQuestionId(que);
		surveyDetails.setAnswerId(option1);

		surveyDeitailsRepository.save(surveyDetails);

		QAndA qAndA = new QAndA();

		qAndA.setQuestion(que);
		qAndA.setOption1(option1);
		qAndA.setOption2(option2);

		qAndARepository.save(qAndA);

	}*/
}
