package com.insuranceManagementPlatform.entity;

import java.util.ArrayList;
import java.util.List;

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
	private String email;
	private String mobileNumber;
	private String name;
	private String dateOfBirth;
	private String address;
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
	private List<InsurancePolicy> insurancePolicies = new ArrayList<>();

}
