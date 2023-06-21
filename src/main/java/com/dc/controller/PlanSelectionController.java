package com.dc.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dc.dto.PlanSelectionDto;
import com.dc.dto.PlanSelectionRequestDto;
import com.dc.service.DcService;

@RestController
public class PlanSelectionController {
	
	
	private static final Logger log = LoggerFactory.getLogger(PlanSelectionController.class);
	
	@Autowired
	private DcService dcService;
	
	@PostMapping("/planselection")
	public ResponseEntity<Long> savePlanToCase(@RequestBody PlanSelectionRequestDto planSelectionDto){
		log.info("savePlan");
		
		Long caseNum = dcService.savePlanToCase(planSelectionDto);
		
		return new ResponseEntity<Long>(caseNum,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/plans")
	public ResponseEntity<Map<Integer,String>> findAllPlans(){
		log.info("findAllPlans");
		
		Map<Integer, String> plans = dcService.findAllPlans();
		
		return new ResponseEntity<Map<Integer,String>>(plans,HttpStatus.OK);
	}
	
}
