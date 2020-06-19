package com.covidchecklist.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.covidchecklist.app.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

	Employee findByEmployeeId(Integer empId);
	
}
