package com.covidchecklist.app.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;

@Entity
@Data
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
	
	@JsonManagedReference
	@Exclude
	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Survey> survey;
}
