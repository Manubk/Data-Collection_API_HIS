package com.dc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dc.dto.PlanSelectionDto;
import com.dc.service.DcService;

@RestController
public class CreateCaseController {
	
	
	private static final Logger log = LoggerFactory.getLogger(CreateCaseController.class);

	@Autowired
	private DcService dcService;
	
	@GetMapping("/createcase/{appId}")
	public ResponseEntity<Long> createCase(Long appId){
		log.info("createCase appId = "+appId);
		
		Long caseNum = dcService.loadCaseNum(appId);
		
		return new ResponseEntity<Long>(caseNum,HttpStatus.CREATED);
	}
	
}
