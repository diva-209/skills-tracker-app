package com.nhs.uk.skillstracker.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@JsonInclude(value = Include.NON_NULL)
public class Skill {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer skillId;
	
	private String skillName;
	
	private String skillLevel;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_Id", nullable = false)
	@JsonIgnore
	private Employee employee;
	
	
}
