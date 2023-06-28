package com.dc.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import com.dc.dto.PlanSelectionDto;
import com.dc.entity.DcCase;
import com.dc.repo.DcCaseRepo;
import com.dc.service.DcService;

@ExtendWith(MockitoExtension.class)
public class DcServiceTest {
	
	@InjectMocks
	private DcService dcService;
	
	@Spy
	private DcService dcServiceSpy;
	
	@Mock
	private DcCaseRepo caseRepo;
	
	
	@ParameterizedTest
	@CsvSource({"1,1","2,0"})
	public void loadCaseNumTest(Long inputAppId ,Long caseNum) {
		
	DcCase dcCase = new DcCase();
	dcCase.setAppId(inputAppId);
	dcCase.setCaseNum(caseNum);

	Mockito.lenient().when(caseRepo.save(any(DcCase.class))).thenReturn(dcCase);

	Long actualavalue = dcService.loadCaseNum(inputAppId);
	
	assertEquals(caseNum, actualavalue);
	}
	
	@ParameterizedTest
	@CsvSource({"1,1","1,0"})
	public void createCaseNumTest(Long appId,Long CaseNum) {
		
		DcCase dcCase = new DcCase();
		dcCase.setAppId(appId);
		dcCase.setCaseNum(CaseNum);
		
		HashMap<Integer, String> plans = new HashMap<>();
		plans.put(1, "SNAP");
		plans.put(2, "CCAP");
		
		PlanSelectionDto plan = new PlanSelectionDto();
		plan.setCaseNum(CaseNum);
		plan.setPlans(plans);
		
		Mockito.lenient().when(caseRepo.save(any(DcCase.class))).thenReturn(dcCase);
		
		DcService spy = Mockito.spy(DcService.class);
		
		Mockito.doReturn(plans).when(spy).findAllPlans();
		
		
		PlanSelectionDto actualValue = dcService.createCaseNum(appId);
		assertNull(actualValue);
		
	}
	
	@ParameterizedTest
	@CsvSource({"1234567890,******7890","4545454545,******4545"})
	public void starPasswordTest(String input, String output) {
		
		
		String actualOutput = dcService.starPassword(input);
		
		assertEquals(output, actualOutput);
		
	}
}
