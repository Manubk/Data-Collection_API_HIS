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

import com.dc.dto.ChildrenRequestDto;
import com.dc.dto.DcSummery;
import com.dc.serviceinterface.IDcService;

@CrossOrigin
@RestController
public class ChildrenController {

	
	private static final Logger log = LoggerFactory.getLogger(ChildrenController.class);

	@Autowired
	private IDcService dcService;
	
	@PostMapping("/children")
	public ResponseEntity<DcSummery> saveChildrens(@RequestBody ChildrenRequestDto childrenDto){
		log.info("saveChildrens caseNum = "+childrenDto.getCaseNum());
		
		Long caseNum = dcService.saveChildrens(childrenDto);
		
		DcSummery dcSummery = dcService.getDCSummery(caseNum);
		
		return new ResponseEntity<DcSummery>(dcSummery,HttpStatus.CREATED);
	}
}
