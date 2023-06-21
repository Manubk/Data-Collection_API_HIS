package com.dc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dc.dto.IncomeDto;
import com.dc.serviceinterface.IDcService;

@RestController
public class IncomeController {
	
	
	private static final Logger log = LoggerFactory.getLogger(IncomeController.class);

	@Autowired
	private IDcService dcService;
	
	@PostMapping("/income")
	public ResponseEntity<Long> saveIncome(@RequestBody IncomeDto incomeDto){
		log.info("saveIncome caseNum = "+incomeDto.getCaseNum());
		
		Long caseNum = dcService.saveIncome(incomeDto);
		
		return new ResponseEntity<Long>(caseNum,HttpStatus.CREATED);
	}
}
