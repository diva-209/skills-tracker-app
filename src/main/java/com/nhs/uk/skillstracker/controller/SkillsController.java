package com.nhs.uk.skillstracker.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import com.nhs.uk.skillstracker.model.Skill;
import com.nhs.uk.skillstracker.repository.EmployeeRepository;
import com.nhs.uk.skillstracker.repository.SkillsRepository;

@RestController
@RequestMapping("/api/v1/")
public class SkillsController {

	@Autowired
	private SkillsRepository skillsRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	private static List<Skill> addedSkills = new ArrayList<Skill>();

	@GetMapping("/employees/{employeeId}/skills")
	public List<Skill> getSkillsByEmployeeId(@PathVariable Integer employeeId) throws ProblemDetailsException {

		if (!employeeRepository.existsById(employeeId)) {
			throw new ProblemDetailsException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"Employee not found with id " + employeeId);
		}
		return skillsRepository.findByEmployeeId(employeeId);
	}

	@PostMapping("/employees/{employeeId}/skills")
	public List<Skill> addSkillsToEmployee(@PathVariable Integer employeeId, @Valid @RequestBody List<Skill> skills)
			throws ProblemDetailsException {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if (employee.isPresent()) {
			for (Skill skill : skills) {
				boolean isSkillValid = validateSkill(skill);
				if (isSkillValid) {
					skill.setEmployee(employee.get());
					addedSkills.add(skill);
				} else {
					throw new ProblemDetailsException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(),
							"Invalid value for skillLevel. Must be among Expert/Practitioner/Working/Awareness");
				}
			}

		} else {
			new ProblemDetailsException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"Employee not found with id " + employeeId);
		}
		return skillsRepository.saveAll(addedSkills);

	}

	@PutMapping("/employees/{employeeId}/skills/{skillId}")
	public Skill updateSkill(@PathVariable Integer employeeId, @PathVariable Integer skillId,
			@Valid @RequestBody Skill updatedSkill) throws ProblemDetailsException {

		if (!employeeRepository.existsById(employeeId)) {
			throw new ProblemDetailsException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"Employee not found with id " + employeeId);
		}

		Optional<Skill> skill = skillsRepository.findById(skillId);
		Skill skillToUpdate = skill.get();
		if (!skill.isPresent()) {
			throw new ProblemDetailsException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"Skill not found with id " + skillId);
		} else {

			boolean isValid = validateSkill(updatedSkill);
			if (isValid) {
				skillToUpdate.setSkillName(updatedSkill.getSkillName());
				skillToUpdate.setSkillLevel(updatedSkill.getSkillLevel());
			} else {
				throw new ProblemDetailsException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(),
						"Invalid value for skillLevel. Must be among Expert/Practitioner/Working/Awareness");
			}
		}
		return skillsRepository.save(skillToUpdate);

	}

	@DeleteMapping("/employees/{employeeId}/skills/{skillId}")
	public String deleteSkill(@PathVariable Integer employeeId, @PathVariable Integer skillId)
			throws ProblemDetailsException {

		if (!employeeRepository.existsById(employeeId)) {
			throw new ProblemDetailsException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"Employee not found with id " + employeeId);
		}

		return skillsRepository.findById(skillId).map(skill -> {
			skillsRepository.delete(skill);
			return "Skill deleted successfully !";
		}).orElseThrow(() -> new ProblemDetailsException(HttpStatus.BAD_REQUEST,
				HttpStatus.BAD_REQUEST.getReasonPhrase(), "Skill not found with id " + skillId));
	}

	private boolean validateSkill(Skill skill) {
		String skillLevel = skill.getSkillLevel();
		List<String> validSkillLevels = Arrays.asList("Expert", "Practitioner", "Working", "Awareness");
		if (validSkillLevels.contains(skillLevel))
			return true;
		else
			return false;
	}
}
