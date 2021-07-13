/**
 * 
 */
package com.nhs.uk.skillstracker.util;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhs.uk.skillstracker.model.Employee;
import com.nhs.uk.skillstracker.model.Skill;

/**
 * class which has utility methods.
 *
 */
public class Utility {
	
	public String getJsonFromObject(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	public List<Employee> getMockDataForMultipleEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		
		Employee employee1 = new Employee();
		Employee employee2 = new Employee();
		
		employee1.setId(1);
		employee1.setFirstName("Divakar");
		employee1.setLastName("Siriki");
		
		employee2.setId(2);
		employee2.setFirstName("Anand");
		employee2.setLastName("Satya");
		
		employees.add(employee1);
		employees.add(employee2);
		
		return employees;
	}
	
	
	public List<Skill> getMockDataForMultipleSkills() {
        List<Skill> skills = new ArrayList<Skill>();
        List<Employee> employees = getMockDataForMultipleEmployees();
		
        Skill skill1 = new Skill();
        Skill skill2 = new Skill();
		
        skill1.setSkillId(1);
		skill1.setSkillName("SpringBoot");
		skill1.setSkillLevel("Expert");
		skill1.setEmployee(employees.get(0));
		
		skill2.setSkillId(2);
		skill2.setSkillName("Microservices");
		skill2.setSkillLevel("Awareness");
		skill2.setEmployee(employees.get(0));
		
		skills.add(skill1);
		skills.add(skill2);
		return skills;
	}

}
