package com.dc.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Auditable {
	
	@CreationTimestamp
	@Column(name = "CREATED_AT",updatable = false)
	private LocalDate createdAt;
	
	@Column(name = "UPDATED_AT", insertable = true)
	private LocalDate updatedAt;
	
	private Long createdBy;
	
	private Long UpdatedBy;
}
