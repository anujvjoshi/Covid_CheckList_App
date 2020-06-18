package com.covidchecklist.app.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	public Employee(String employeeId, String name, String email) {
		this.employeeId = employeeId;
		this.name = name;
		this.email = email;
	}

	@Id
	@Column(name = "emp_id", nullable = false)
	private String employeeId;

	@Column(name = "name", nullable = false)
	String name;

	@Column(name = "email", nullable = false)
	String email;
	
	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Survey> survey;
}
