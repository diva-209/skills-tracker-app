package com.nhs.uk.skillstracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhs.uk.skillstracker.exceptions.ProblemDetailsException;
import com.nhs.uk.skillstracker.model.Employee;
import com.nhs.uk.skillstracker.repository.EmployeeRepository;

/**
 * This is the service class which is used to develop business logic 
 * 
 *
 */
@Service
public class SkillsTrackerService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> addEmployees(List<Employee> employees) throws ProblemDetailsException {
		
		return employeeRepository.saveAll(employees);
		
	}
}
