package com.covidchecklist.app.service;

import java.util.List;

import com.covidchecklist.app.dto.EmployeeDTO;

public interface EmployeeService {

	List<EmployeeDTO> getAllEmployeeDetails();

	void saveEmployeesToDB();

	boolean validateEmployee(String empId);
}
