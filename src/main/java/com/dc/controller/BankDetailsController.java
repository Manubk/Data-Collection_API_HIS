package com.dc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dc.dto.BankDto;
import com.dc.serviceinterface.IDcService;

@CrossOrigin
@RestController
public class BankDetailsController {
	
	
	private static final Logger log = LoggerFactory.getLogger(BankDetailsController.class);

	@Autowired
	private IDcService dcService;
	
	@PostMapping("/bank")
	public ResponseEntity<Long> saveBank(@RequestBody BankDto bankDto){
		log.info("saveBank");
		
		Long caseNum = dcService.saveBankDetails(bankDto);
		
		return new ResponseEntity<Long>(caseNum,HttpStatus.OK);
		
	}
	

}
