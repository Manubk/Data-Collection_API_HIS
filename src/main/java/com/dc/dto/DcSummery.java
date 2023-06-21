package com.dc.dto;

import java.util.List;

import lombok.Data;

@Data
public class DcSummery {
	
	private Long caseNum;
	
	private String planName;
	
	private IncomeDto incomeDto;
	
	private EducationDto educationDto;
	
	private List<ChildrenDto> childrenDtos;
	
	private String ssn;
	
}
