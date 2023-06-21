package com.dc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dc.dto.EducationDto;
import com.dc.serviceinterface.IDcService;

@RestController
public class EducationController {

	
	private static final Logger log = LoggerFactory.getLogger(EducationController.class);

	@Autowired
	private IDcService dcService;
	
	@PostMapping("/education")
	public ResponseEntity<Long> saveEducation(@RequestBody EducationDto educationDto){
		log.info("saveEducation caseNum = "+educationDto.getCaseNum());
		
		Long caseNum = dcService.saveEducation(educationDto);
		
		return new ResponseEntity<Long>(caseNum,HttpStatus.CREATED);
		
	}
	
}
