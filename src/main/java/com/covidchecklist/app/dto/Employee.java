package com.covidchecklist.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	String employeeId;
	
	String firstName;
	
	String lastName;
	
	String email;
}