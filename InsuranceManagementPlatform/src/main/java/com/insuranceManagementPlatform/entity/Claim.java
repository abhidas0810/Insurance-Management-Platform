package com.insuranceManagementPlatform.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Claim {

	@Id
	@GeneratedValue
	private Integer claimNumber;
	@NotNull
	private String description;
	@Past(message = "The claim date must be in the past.")
	@NotNull
	private Date claimDate;
	@NotNull
	@Pattern(regexp = "^(Pending|Approved|Rejected)$", message = "Claim status must be either Pending or Approved or Rejected.")
	private String claimStatus;

	@ManyToOne
	private InsurancePolicy insurancePolicy;

}
