package com.dc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dc.dto.DcSummery;
import com.dc.serviceinterface.IDcService;

@RestController
public class SummaryController {
	
	
	private static final Logger log = LoggerFactory.getLogger(SummaryController.class);
	
	@Autowired
	private IDcService dcService;
	
	@GetMapping("/summary/{caseNum}")
	public ResponseEntity<DcSummery> getSummary(Long caseNum){
		log.info("getSummary caseNum = "+caseNum);
		
		DcSummery dcSummery = dcService.getDCSummery(caseNum);
		
		return new ResponseEntity<DcSummery>(dcSummery,HttpStatus.OK);
	}
}
