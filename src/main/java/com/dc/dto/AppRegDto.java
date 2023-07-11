package com.dc.dto;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

import lombok.Data;

@Data
public class AppRegDto {

	private Long appId;
	
	private String fullName;
	
	private String email;
	
	private String phoneNo;
	
	private String gender;
	
	private String stateName;
	
	private String ssn;
	
	private LocalDate dob;
}
