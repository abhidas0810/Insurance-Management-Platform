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

import com.insuranceManagementPlatform.entity.Claim;
import com.insuranceManagementPlatform.exception.ClaimException;
import com.insuranceManagementPlatform.exception.InsurancePolicyException;
import com.insuranceManagementPlatform.service.ClaimService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ClaimController {

	/**
	 * claimService : dependency of ClaimService.
	 */
	@Autowired
	private ClaimService claimService;

	/**
	 * taking the request from the client and creating new claim.
	 */
	@PostMapping("/claims")
	public ResponseEntity<Claim> createNewClaimHandler(@RequestParam(name = "policyNumber") Integer policyNumber,
			@Valid @RequestBody Claim claim) throws ClaimException, InsurancePolicyException {
		return new ResponseEntity<Claim>(claimService.createNewClaim(policyNumber, claim), HttpStatus.CREATED);
	}

	/**
	 * taking the request from the client and returning details of specific claim by
	 * claim number.
	 */
	@GetMapping("/claims/{id}")
	public ResponseEntity<Claim> fetchClaimHandler(@PathVariable("id") Integer claimNumber) throws ClaimException {
		return new ResponseEntity<Claim>(claimService.fetchClaim(claimNumber), HttpStatus.OK);
	}

	/**
	 * taking the request from the client and returning details of all claims.
	 */
	@GetMapping("/claims")
	public ResponseEntity<List<Claim>> fetchClaimsHandler() throws ClaimException {
		return new ResponseEntity<List<Claim>>(claimService.fetchClaims(), HttpStatus.OK);
	}

	/**
	 * taking the request from the client and returning updated details of specific
	 * claim by claim number.
	 */
	@PutMapping("/claims/{id}")
	public ResponseEntity<Claim> updateClaimHandler(@PathVariable("id") Integer claimNumber, @Valid @RequestBody Claim claim)
			throws ClaimException {
		return new ResponseEntity<Claim>(claimService.updateClaim(claimNumber, claim), HttpStatus.ACCEPTED);
	}

	/**
	 * taking the request from the client and returning deleted details of specific
	 * claim by claim number from the database.
	 */
	@DeleteMapping("/claims/{id}")
	public ResponseEntity<Claim> deleteClaimHandler(@PathVariable("id") Integer claimNumber) throws ClaimException {
		return new ResponseEntity<Claim>(claimService.deleteClaim(claimNumber), HttpStatus.OK);
	}

}
