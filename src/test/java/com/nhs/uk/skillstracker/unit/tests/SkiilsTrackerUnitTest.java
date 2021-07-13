package com.nhs.uk.skillstracker.unit.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nhs.uk.skillstracker.controller.EmployeesController;
import com.nhs.uk.skillstracker.controller.SkillsController;
import com.nhs.uk.skillstracker.exceptions.ProblemDetailsException;
import com.nhs.uk.skillstracker.model.Employee;
import com.nhs.uk.skillstracker.model.Skill;
import com.nhs.uk.skillstracker.repository.EmployeeRepository;
import com.nhs.uk.skillstracker.repository.SkillsRepository;
import com.nhs.uk.skillstracker.util.Utility;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkiilsTrackerUnitTest {


	@MockBean
	private EmployeeRepository employeeRepo;
	
	@MockBean
	private SkillsRepository skillsRepo;

	@Autowired
	private SkillsController skillsController;
	
	@Autowired
	private EmployeesController employeeController;
	
	private Utility utility = new Utility();
	
	@Test
	public void testAddMultipleEmployees() throws ProblemDetailsException {
		List<Employee> employees = utility.getMockDataForMultipleEmployees();
		when(employeeRepo.saveAll(employees)).thenReturn(employees);
		List<Employee> response = employeeController.addEmployee(employees);
		assertEquals(2,response.size());
		assertNotNull(response.get(0).getId());
		assertEquals("Divakar",response.get(0).getFirstName());
	}
	

	@Test
	public void testRetrieveAllEmployeeDetails() throws Exception {
		
		when(employeeRepo.findAll())
				.thenReturn(utility.getMockDataForMultipleEmployees());

		List<Employee> storesInfo = employeeController.getAllEmployees();

		assertEquals(2, storesInfo.size());

	}
	
	@Test
	public void testGetEmployeeWithId() throws Exception {
		
		List<Employee> employees = utility.getMockDataForMultipleEmployees();
		Optional<Employee> employee = Optional.of(employees.get(0));
		
		when(employeeRepo.findById(1))
				.thenReturn(employee);

		Employee response = employeeController.getEmployeeByID(1);

		assertNotNull(response);
		assertEquals(1,response.getId());
		assertEquals("Divakar",response.getFirstName());

	}
	
	@Test
	public void testGetSkillsForEmployeeId() throws Exception {
		
		List<Skill> skills = utility.getMockDataForMultipleSkills();
		
		when(skillsRepo.findByEmployeeId(1))
				.thenReturn(skills);
		
        when(employeeRepo.existsById(1)).thenReturn(true);
        
		List<Skill> response = skillsController.getSkillsByEmployeeId(1);

		assertNotNull(response);
		assertEquals(1,response.get(0).getSkillId());
		assertEquals("SpringBoot",response.get(0).getSkillName());

	}
	
	
	


}
