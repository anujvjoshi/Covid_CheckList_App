package com.covidchecklist.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidchecklist.app.dto.QAndADTO;
import com.covidchecklist.app.entities.AnswerOptions;
import com.covidchecklist.app.entities.QAndA;
import com.covidchecklist.app.entities.Question;
import com.covidchecklist.app.modelmapper.Covid19ModelMapper;
import com.covidchecklist.app.repository.AnswerOptionRepository;
import com.covidchecklist.app.repository.QAndARepository;
import com.covidchecklist.app.repository.QuestionRepository;
import com.covidchecklist.app.service.QuestionOptionService;

@Service
public class QuestionOptionServiceImpl implements QuestionOptionService {

	@Autowired
	ReadExcelService readExcelService;

	@Autowired
	QAndARepository qAndARepository;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	AnswerOptionRepository answerOptionRepository;

	@Autowired
	Covid19ModelMapper mapper;

	@Override
	public void saveQuestionsToDB() {
		List<QAndA> qAndAs = readExcelService.readQAndADataFromExcel();

		qAndAs.stream().forEach(q -> {
			q.setQuestion(saveOrGetQuestion(q.getQuestion()));
			q.setOption1(saveOrGetOption(q.getOption1()));
			q.setOption2(saveOrGetOption(q.getOption2()));
		});

		List<QAndA> savedQuestionOptions = qAndARepository.findAll();
		List<String> lstQuestion = savedQuestionOptions.stream().map(e -> e.getQuestion().getQuestion()).collect(Collectors.toList());
		List<QAndA> allQAndAns = qAndAs.stream().filter(e -> !lstQuestion.contains(e.getQuestion().getQuestion()))
				.collect(Collectors.toList());

		qAndARepository.saveAll(allQAndAns);
		
	}

	private AnswerOptions saveOrGetOption(AnswerOptions option) {

		AnswerOptions savedOption = answerOptionRepository.findByOption(option.getOption());

		if (savedOption == null) {
			savedOption = answerOptionRepository.save(option);
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
	public List<QAndADTO> getAllQuestionsWithOptions() {

		List<QAndA> questionList = qAndARepository.findAll();

		return mapper.map(questionList, new TypeToken<List<QAndADTO>>() {
		}.getType());

	}

}
