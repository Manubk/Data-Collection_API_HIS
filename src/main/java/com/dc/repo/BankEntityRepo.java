package com.dc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.entity.BankEntity;

public interface BankEntityRepo extends JpaRepository<BankEntity, Long> {
	
	public BankEntity findByCaseNum(Long caseNum);
}
