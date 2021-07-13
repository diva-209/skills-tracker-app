package com.nhs.uk.skillstracker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhs.uk.skillstracker.exceptions.ProblemDetailsException;
import com.nhs.uk.skillstracker.model.Employee;
import com.nhs.uk.skillstracker.repository.EmployeeRepository;

/**
 * This is the controller which receives the request and performs the respective
 * operation.
 * 
 *
 */
@RestController
@RequestMapping("/api/v1/")
public class EmployeesController {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * This method retrieves all employees in organization
	 * 
	 * @return
	 */
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	/**
	 * This method retrieves the employee details with given id
	 * 
	 * @param id
	 * @return
	 * @throws ProblemDetailsException
	 */
	@GetMapping("/employees/{id}")
	public Employee getEmployeeByID(@PathVariable Integer id) throws ProblemDetailsException {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new ProblemDetailsException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"Employee not found with id " + id);
		}
	}

	/**
	 * This method adds the list of employees .
	 * 
	 * @param employee
	 * @return
	 * @throws ProblemDetailsException
	 */
	@PostMapping("/employees")
	public List<Employee> addEmployee(@RequestBody List<Employee> employee) throws ProblemDetailsException {

		return employeeRepository.saveAll(employee);

		
	}

	/**
	 * This method updates the employee record in organization for a given employee
	 * id
	 * 
	 * @param id
	 * @param updatedEmployee
	 * @return
	 * @throws ProblemDetailsException
	 */
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee)
			throws ProblemDetailsException {
		return employeeRepository.findById(id).map(employee -> {
			employee.setFirstName(updatedEmployee.getFirstName());
			employee.setLastName(updatedEmployee.getLastName());
			return employeeRepository.save(employee);
		}).orElseThrow(() -> new ProblemDetailsException(HttpStatus.BAD_REQUEST,
				HttpStatus.BAD_REQUEST.getReasonPhrase(), "Employee not found with id " + id));
	}

	/**
	 * This method deletes the employee record for a given employee id
	 * 
	 * @param id
	 * @return
	 * @throws ProblemDetailsException
	 */
	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable Integer id) throws ProblemDetailsException {
		return employeeRepository.findById(id).map(employee -> {
			employeeRepository.delete(employee);
			return "Employee got Deleted Successfully!";
		}).orElseThrow(() -> new ProblemDetailsException(HttpStatus.BAD_REQUEST,
				HttpStatus.BAD_REQUEST.getReasonPhrase(), "Employee not found with id " + id));
	}
}
