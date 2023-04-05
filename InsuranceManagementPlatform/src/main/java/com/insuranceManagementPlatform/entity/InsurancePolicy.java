package com.insuranceManagementPlatform.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class InsurancePolicy {

	@Id
	@GeneratedValue
	private Integer policyNumber;
	@NotNull
	private String type;
	@NotNull
	@Positive(message = "The coverage amount must be a positive number.")
	private Double coverageAmount;
	@NotNull
	@Positive(message = "The premium amount must be a positive number.")
	private Double premium;
	@NotNull
	@Future(message = "The start date must be a valid date and cannot be in the past.")
	private Date startDate;
	@NotNull
	@Future(message = "The end date must be a valid date and cannot be in the past.")
	private Date endDate;

	@ManyToOne
	private Client client;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "insurancePolicy")
	@JsonIgnore
	private List<Claim> claims = new ArrayList<>();

}
