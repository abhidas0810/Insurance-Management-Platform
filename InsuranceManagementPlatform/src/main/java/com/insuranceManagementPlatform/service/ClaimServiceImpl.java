package com.insuranceManagementPlatform.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insuranceManagementPlatform.entity.Claim;
import com.insuranceManagementPlatform.entity.InsurancePolicy;
import com.insuranceManagementPlatform.exception.ClaimException;
import com.insuranceManagementPlatform.exception.InsurancePolicyException;
import com.insuranceManagementPlatform.repository.ClaimRepository;
import com.insuranceManagementPlatform.repository.InsurancePolicyRepository;

@Service
public class ClaimServiceImpl implements ClaimService {

	/**
	 * claimRepository : Dependency of ClaimRepository.
	 */
	@Autowired
	private ClaimRepository claimRepository;

	/**
	 * insurancePolicyRepository : Dependency of InsurancePolicyRepository.
	 */
	@Autowired
	private InsurancePolicyRepository insurancePolicyRepository;

	/**
	 * Create a new claim.
	 */
	@Override
	public Claim createNewClaim(Integer policyNumber, Claim claim) throws ClaimException, InsurancePolicyException {
		InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(policyNumber)
				.orElseThrow(() -> new InsurancePolicyException("Insurance Policy not found for which claim is made."));
		claim.setInsurancePolicy(insurancePolicy);
		insurancePolicy.getClaims().add(claim);
		Claim registeredClaim = claimRepository.save(claim);
		if (registeredClaim == null) {
			throw new ClaimException("can't make claim.");
		}
		return registeredClaim;
	}

	/**
	 * Fetch a specific claim by ID.
	 */
	@Override
	public Claim fetchClaim(Integer claimNumber) throws ClaimException {
		return claimRepository.findById(claimNumber)
				.orElseThrow(() -> new ClaimException("no claim found with claimNumber " + claimNumber));
	}

	/**
	 * Fetch all claims.
	 */
	@Override
	public List<Claim> fetchClaims() throws ClaimException {
		List<Claim> claims = claimRepository.findAll();
		if (claims.isEmpty()) {
			throw new ClaimException("no claim found.");
		}
		return claims;
	}

	/**
	 * Update a claim's information.
	 */
	@Override
	public Claim updateClaim(Integer claimNumber, Claim claim) throws ClaimException {
		if (claimNumber != claim.getClaimNumber()) {
			throw new ClaimException("claimNumber mismatch.");
		}
		Claim registeredClaim = claimRepository.findById(claimNumber)
				.orElseThrow(() -> new ClaimException("no claim found with claimNumber " + claimNumber));		
		registeredClaim.setClaimDate(claim.getClaimDate());
		registeredClaim.setClaimStatus(claim.getClaimStatus());
		registeredClaim.setDescription(claim.getDescription());
		return claimRepository.save(registeredClaim);
	}

	/**
	 * Delete a claim.
	 */
	@Override
	public Claim deleteClaim(Integer claimNumber) throws ClaimException {
		Claim registeredClaim = claimRepository.findById(claimNumber)
				.orElseThrow(() -> new ClaimException("no claim found with claimNumber " + claimNumber));
		claimRepository.delete(registeredClaim);
		return registeredClaim;
	}

}
