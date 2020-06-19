package com.covidchecklist.app.modelmapper;

import javax.annotation.PostConstruct;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covidchecklist.app.dto.QAndADTO;
import com.covidchecklist.app.dto.SurveyDTO;
import com.covidchecklist.app.dto.SurveyDetailsDTO;
import com.covidchecklist.app.entities.QAndA;
import com.covidchecklist.app.entities.Survey;
import com.covidchecklist.app.entities.SurveyDetails;

@Component
public class ModelMapping {

	@Autowired
	Covid19ModelMapper mapper;

	@PostConstruct
	void registerModelMapping() {

		mapper.getConfiguration().setAmbiguityIgnored(true);
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		mapper.typeMap(QAndA.class, QAndADTO.class).addMappings((mapper -> {

			mapper.map(src -> src.getQuestion().getQuestionId(), QAndADTO::setQuestionId);
			mapper.map(src -> src.getQuestion().getQuestion(), QAndADTO::setQuestion);

			mapper.map(src -> src.getOption1().getOptionId(), QAndADTO::setOption1Id);
			mapper.map(src -> src.getOption1().getOption(), QAndADTO::setOption1);

			mapper.map(src -> src.getOption2().getOptionId(), QAndADTO::setOption2Id);
			mapper.map(src -> src.getOption2().getOption(), QAndADTO::setOption2);

		}));

		mapper.typeMap(Survey.class, SurveyDTO.class).addMappings((mapper -> {

			mapper.map(src -> src.getEmployee().getEmployeeId(), SurveyDTO::setEmpId);
			mapper.map(src -> src.getEmployee().getName(), SurveyDTO::setEmpName);
			mapper.map(src -> src.getEmployee().getEmail(), SurveyDTO::setEmail);
			
			mapper.map(src -> src.getDate(), SurveyDTO::setDate);

		}));

		mapper.typeMap(SurveyDetails.class, SurveyDetailsDTO.class).addMappings((mapper -> {

			mapper.map(src -> src.getSurveyId(), SurveyDetailsDTO::setSurveyId);
			mapper.map(src -> src.getQuestionId().getQuestionId(), SurveyDetailsDTO::setQuestionId);
			mapper.map(src -> src.getQuestionId().getQuestion(), SurveyDetailsDTO::setQuestion);

			mapper.map(src -> src.getAnswerId().getOptionId(), SurveyDetailsDTO::setAnswerId);
			mapper.map(src -> src.getAnswerId().getOption(), SurveyDetailsDTO::setAnswer);
		}));

	}

}
