package com.covidchecklist.app.entities;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Survey {

	Employee employee;
	
	List<QAndA> QandAs;
	
	Date date; 
}
