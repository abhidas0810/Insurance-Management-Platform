package com.insuranceManagementPlatform.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Claim {

	@Id
	@GeneratedValue
	private Integer claimNumber;
	private String description;
	private Date claimDate;
	private String claimStatus;

	@ManyToOne
	private InsurancePolicy insurancePolicy;

}
