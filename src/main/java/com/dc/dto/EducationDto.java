package com.dc.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EducationDto {
	
	private Long caseNum;
	
	private String highestQual; 
	
	private LocalDate gradYear;
	
	private String universityName;
}
