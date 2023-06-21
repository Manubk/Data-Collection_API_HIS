package com.dc.dto;

import java.util.Map;

import lombok.Data;

@Data
public class PlanSelectionDto {
	
	private Long caseNum;
	
	private Map<Integer,String> plans;
}
