package com.covidchecklist.app.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.covidchecklist.app.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

	@Cacheable(value = "empCache", key = "#empId")
	Employee findByEmployeeId(String empId);
	
}
