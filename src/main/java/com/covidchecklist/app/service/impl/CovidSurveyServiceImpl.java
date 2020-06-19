package com.covidchecklist.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidchecklist.app.dto.EmployeeDTO;
import com.covidchecklist.app.entities.AnswerOptions;
import com.covidchecklist.app.entities.Employee;
import com.covidchecklist.app.entities.QAndA;
import com.covidchecklist.app.entities.Question;
import com.covidchecklist.app.modelmapper.Covid19ModelMapper;
import com.covidchecklist.app.repository.AnswerRepository;
import com.covidchecklist.app.repository.EmployeeRepository;
import com.covidchecklist.app.repository.QAndARepository;
import com.covidchecklist.app.repository.QuestionRepository;
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

	@Autowired
	QAndARepository qAndARepository;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	AnswerRepository answerRepository;

	@Autowired
	Covid19ModelMapper mapper;

	@Override
	public List<EmployeeDTO> getAllEmployeeDetails() {
		List<Employee> empList = (List<Employee>) employeeRepository.findAll();
		return mapper.map(empList, new TypeToken<List<EmployeeDTO>>() {
		}.getType());
	}

	@Override
	public boolean validateEmployee(String empId) {
		List<String> dbEmployees = getAllEmployeeDetails().stream().map(e -> e.getEmployeeId())
				.collect(Collectors.toList());
		return dbEmployees.stream().anyMatch(e -> e.equalsIgnoreCase(empId));
	}

	@Override
	public void saveQuestionsToDB() {
		List<QAndA> qAndAs = readExcelService.readQAndADataFromExcel();

		qAndAs.stream().forEach(q -> {
			q.setQuestion(saveOrGetQuestion(q.getQuestion()));
			q.setOption1(saveOrGetOption(q.getOption1()));
			q.setOption2(saveOrGetOption(q.getOption2()));
		});

		List<Question> questions = questionRepository.findAll();
		List<String> lstQuestion = questions.stream().map(e -> e.getQuestion()).collect(Collectors.toList());
		List<QAndA> allQAndAns = qAndAs.stream().filter(e -> !lstQuestion.contains(e.getQuestion().getQuestion()))
				.collect(Collectors.toList());

		qAndARepository.saveAll(allQAndAns);
	}

	@Override
	public void saveEmployeesToDB() {

		List<String> dbEmployees = getAllEmployeeDetails().stream().map(e -> e.getEmployeeId())
				.collect(Collectors.toList());
		List<Employee> employees = readExcelService.readEmployeeDataFromExcel().stream()
				.filter(e -> !dbEmployees.contains(e.getEmployeeId())).collect(Collectors.toList());

		employeeRepository.saveAll(employees);
	}

	private AnswerOptions saveOrGetOption(AnswerOptions option) {

		AnswerOptions savedOption = answerRepository.findByOption(option.getOption());

		if (savedOption == null) {
			savedOption = answerRepository.save(option);
		}

		return savedOption;

	}

	private Question saveOrGetQuestion(Question question) {

		Question savedQuestion = questionRepository.findByQuestion(question.getQuestion());
		if (savedQuestion == null) {
			savedQuestion = questionRepository.save(question);
		}
		return savedQuestion;
	}

	@Override
	public void getEntireSurveyData() {
		surveyRepository.findAll();
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
