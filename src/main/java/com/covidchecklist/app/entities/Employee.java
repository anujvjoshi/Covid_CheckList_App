package com.covidchecklist.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@Column(name = "emp_id", nullable = false)
	private String employeeId;

	@Column(name = "first_name", nullable = false)
	String firstName;
	

	@Column(name = "last_name", nullable = false)
	String lastName;
	

	@Column(name = "email", nullable = false)
	String email;
}
