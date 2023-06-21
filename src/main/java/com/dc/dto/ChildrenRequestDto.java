package com.dc.dto;

import java.util.List;

import lombok.Data;

@Data
public class ChildrenRequestDto {
	
	private Long caseNum;
	
	private List<ChildrenDto> childrens;
}
