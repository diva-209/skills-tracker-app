package com.nhs.uk.skillstracker.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nhs.uk.skillstracker.validation.ValueOfEnumValidator.ValueOfEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
	
	//@ValueOfEnum(enumClass = SkillLevel.class, message="Invalid value for skillLevel. Must be among Expert/Practitioner/Working/Awareness")
	private String skillLevel;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_Id", nullable = false)
	@JsonIgnore
	private Employee employee;
	
	
}
