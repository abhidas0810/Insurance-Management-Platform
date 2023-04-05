package com.insuranceManagementPlatform.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Client {

	@Id
	@Email
	private String emailId;
	@Size(min = 10, max = 10, message = "mobile number should be of 10 digits only")
	@NotNull
	private String mobileNumber;
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Name must not contain numbers or special characters")
	@NotNull
	private String name;
	@Past(message = "The date of birth must be in the past.")
	@NotNull
	private Date dateOfBirth;
	@NotNull
	private String address;
	@NotNull
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
	@JsonIgnore
	private List<InsurancePolicy> insurancePolicies = new ArrayList<>();

}
