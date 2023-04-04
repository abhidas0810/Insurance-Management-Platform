package com.insuranceManagementPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insuranceManagementPlatform.entity.InsurancePolicy;
import com.insuranceManagementPlatform.exception.ClientException;
import com.insuranceManagementPlatform.exception.InsurancePolicyException;
import com.insuranceManagementPlatform.service.InsurancePolicyService;

@RestController
@RequestMapping("/api")
public class InsurancePolicyController {
	
	/**
	 * insurancePolicyService : dependency of InsurancePolicyService.
	 */
	@Autowired
	private InsurancePolicyService insurancePolicyService;
	
	/**
	 * taking the request from the client and creating new policy.
	 */
	@PostMapping("/policies")
	public ResponseEntity<InsurancePolicy> createNewInsurancePolicyHandler(@RequestParam(name = "emailId") String emailId,
			@RequestBody InsurancePolicy insurancePolicy) throws InsurancePolicyException, ClientException {
		return new ResponseEntity<InsurancePolicy>(
				insurancePolicyService.createNewInsurancePolicy(emailId, insurancePolicy), HttpStatus.CREATED);
	}
	
	/**
	 * taking the request from the client and returning details of specific insurancePolicy by policy number.
	 */
	@GetMapping("/policies/{id}")
	public ResponseEntity<InsurancePolicy> fetchInsurancePolicyHandler(@PathVariable("id") Integer policyNumber)
			throws InsurancePolicyException {
		return new ResponseEntity<InsurancePolicy>(insurancePolicyService.fetchInsurancePolicy(policyNumber),
				HttpStatus.OK);
	}
	
	/**
	 * taking the request from the client and returning details of all insurancePolicy.
	 */
	@GetMapping("/policies")
	public ResponseEntity<List<InsurancePolicy>> fetchInsurancePoliciesHandler() throws InsurancePolicyException {
		return new ResponseEntity<List<InsurancePolicy>>(insurancePolicyService.fetchInsurancePolicies(),
				HttpStatus.OK);
	}
	
	/**
	 * taking the request from the client and returning updated details of specific insurancePolicy by policy number.
	 */
	@PutMapping("/policies/{id}")
	public ResponseEntity<InsurancePolicy> updateInsurancePolicyHandler(@PathVariable("id") Integer policyNumber,
			@RequestBody InsurancePolicy insurancePolicy) throws InsurancePolicyException {
		return new ResponseEntity<InsurancePolicy>(
				insurancePolicyService.updateInsurancePolicy(policyNumber, insurancePolicy), HttpStatus.ACCEPTED);
	}
	
	/**
	 * taking the request from the client and returning deleted details of specific insurancePolicy by policy number from database.
	 */
	@DeleteMapping("/policies/{id}")
	public ResponseEntity<InsurancePolicy> deleteInsurancePolicyHandler(@PathVariable("id") Integer policyNumber)
			throws InsurancePolicyException {
		return new ResponseEntity<InsurancePolicy>(insurancePolicyService.deleteInsurancePolicy(policyNumber),
				HttpStatus.OK);
	}

}
