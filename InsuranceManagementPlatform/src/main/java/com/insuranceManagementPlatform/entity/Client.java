package com.insuranceManagementPlatform.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Data
public class Client {

	@Id
	@Email
	private String emailId;
	private String mobileNumber;
	private String name;
	private String dateOfBirth;
	private String address;
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
	@JsonIgnore
	private List<InsurancePolicy> insurancePolicies = new ArrayList<>();

}
