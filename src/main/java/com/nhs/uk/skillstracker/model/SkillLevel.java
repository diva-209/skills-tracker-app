package com.nhs.uk.skillstracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SkillLevel {

	EXPERT("Expert"),
	PRACTITIONER("Practitioner"),
	WORKING("Working"),
	AWARENESS("Awareness");
	
	private String value;
}
