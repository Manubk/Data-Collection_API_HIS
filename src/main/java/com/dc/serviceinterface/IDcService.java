package com.dc.serviceinterface;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dc.dto.ChildrenDto;
import com.dc.dto.ChildrenRequestDto;
import com.dc.dto.DcSummery;
import com.dc.dto.EducationDto;
import com.dc.dto.IncomeDto;
import com.dc.dto.PlanSelectionDto;
import com.dc.dto.PlanSelectionRequestDto;
import com.dc.dto.ChildrenDto;

@Service
public interface IDcService {
	
	public Map<Integer ,String> findAllPlans();
	
	public Long loadCaseNum(Long appId);
	
	public PlanSelectionDto createCaseId(Long appId);
	
	public Long savePlanToCase(PlanSelectionRequestDto planSelectionRequestDto);;
	
	public Long saveIncome(IncomeDto incomeDto);
	
	public Long saveEducation(EducationDto educationDto);
	
	public Long saveChildrens(ChildrenRequestDto childrenDtos);
	
	public DcSummery getDCSummery(Long caseNum);
	
	
}
