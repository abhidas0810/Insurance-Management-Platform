package com.insuranceManagementPlatform.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class InsurancePolicy {

	@Id
	@GeneratedValue
	private Integer policyNumber;
	private String type;
	private Double coverageAmount;
	private Double premium;
	private Date startDate;
	private Date endDate;

	@ManyToOne(cascade = CascadeType.ALL)
	private Client client;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "insurancePolicy")
	private List<Claim> claims = new ArrayList<>();

}
