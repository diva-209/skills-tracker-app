package com.nhs.uk.skillstracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhs.uk.skillstracker.model.Skill;

@Repository
public interface SkillsRepository extends JpaRepository<Skill, Integer>{
	
	List<Skill> findByEmployeeId(Integer employeeId);

}
