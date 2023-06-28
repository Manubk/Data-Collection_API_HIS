package com.dc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "BANK_DETAILS")
@Entity
public class BankEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BANK_ID")
	private Long bankId;
	
	@Column(name = "CASE_NUM")
	private Long caseNum;
	
	@Column(name = "BANK_NAME")
	private String bankName;
	
	@Column(name = "ACC_NUMBER")
	private String accNumber;
	
	
	
	
	
}
