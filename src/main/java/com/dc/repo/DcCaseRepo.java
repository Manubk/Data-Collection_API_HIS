package com.dc.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dc.entity.DcCase;
import java.util.List;


@Repository
public interface DcCaseRepo extends JpaRepository<DcCase,Serializable> {
	
	public DcCase findByCaseNum(Long caseNum);
}
