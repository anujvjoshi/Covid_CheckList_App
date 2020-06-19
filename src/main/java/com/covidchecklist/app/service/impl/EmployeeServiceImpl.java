package com.covidchecklist.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidchecklist.app.dto.EmployeeDTO;
import com.covidchecklist.app.entities.Employee;
import com.covidchecklist.app.modelmapper.Covid19ModelMapper;
import com.covidchecklist.app.repository.EmployeeRepository;
import com.covidchecklist.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ReadExcelService readExcelService;

	@Autowired
	Covid19ModelMapper mapper;

	@Override
	public List<EmployeeDTO> getAllEmployeeDetails() {
		List<Employee> empList = (List<Employee>) employeeRepository.findAll();
		return mapper.map(empList, new TypeToken<List<EmployeeDTO>>() {
		}.getType());
	}

	@Override
	public boolean validateEmployee(String empId) {
		List<String> dbEmployees = getAllEmployeeDetails().stream().map(e -> e.getEmployeeId())
				.collect(Collectors.toList());
		return dbEmployees.stream().anyMatch(e -> e.equalsIgnoreCase(empId));
	}

	@Override
	public void saveEmployeesToDB() {

		List<String> dbEmployees = getAllEmployeeDetails().stream().map(e -> e.getEmployeeId())
				.collect(Collectors.toList());
		List<Employee> employees = readExcelService.readEmployeeDataFromExcel().stream()
				.filter(e -> !dbEmployees.contains(e.getEmployeeId())).collect(Collectors.toList());

		employeeRepository.saveAll(employees);
	}

}